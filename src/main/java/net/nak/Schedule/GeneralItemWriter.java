package net.nak.Schedule;

import net.nak.entities.Notification;
import net.nak.services.FileStatusService;
import net.nak.services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class GeneralItemWriter implements ItemWriter<String> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralItemWriter.class);
    private static final String DIRECTORY_PATH = "C:/Repertoire-stock/";
    private static final int TOLERANCE_SECONDS = 5;
    private static final int POLL_INTERVAL_SECONDS = 10;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime scheduledDateTime;
    private final Map<String, Object> fileLocks = new ConcurrentHashMap<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final FileStatusService fileStatusService;
    private final NotificationService notificationService;  // Changed to NotificationService

    @Autowired
    public GeneralItemWriter(FileStatusService fileStatusService, NotificationService notificationService) {
        this.fileStatusService = fileStatusService;
        this.notificationService = notificationService;  // Changed to NotificationService
    }

    @BeforeStep
    public void retrieveScheduledDateTime(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        String scheduledDateTimeStr = jobExecution.getJobParameters().getString("scheduledDateTime");

        if (scheduledDateTimeStr != null) {
            try {
                this.scheduledDateTime = LocalDateTime.parse(scheduledDateTimeStr, DATE_TIME_FORMATTER);
                logger.info("Scheduled DateTime set to: " + this.scheduledDateTime.format(DATE_TIME_FORMATTER));
            } catch (Exception e) {
                logger.error("Error parsing scheduled DateTime: " + scheduledDateTimeStr, e);
                this.scheduledDateTime = LocalDateTime.now();
            }
        } else {
            this.scheduledDateTime = LocalDateTime.now();
            logger.info("No scheduledDateTime provided, defaulting to current DateTime: " + this.scheduledDateTime.format(DATE_TIME_FORMATTER));
        }
    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        logger.info("Current time: " + now.format(DATE_TIME_FORMATTER));

        if (!isScheduledTime(now)) {
            logger.info("Current time does not match the scheduled time. Waiting until the scheduled time.");
            while (!isScheduledTime(LocalDateTime.now())) {
                TimeUnit.SECONDS.sleep(POLL_INTERVAL_SECONDS);
            }
        }

        logger.info("Entering write method");

        if (items.isEmpty()) {
            logger.info("No items to process.");
            return;
        }

        for (String item : items) {
            if (item.contains("#")) {
                String[] parts = item.split("#");
                logger.info("Processing item: " + item);

                executorService.submit(() -> {
                    try {
                        processItem(item, parts);
                        fileStatusService.incrementSuccessfulFiles();
                        String fileName = generateFileName(determineFileType(parts));
                        notificationService.addNotification("Fichier généré avec succès. Nom du fichier: " + fileName);
                    } catch (IOException e) {
                        logger.error("Error processing item: " + item, e);
                        fileStatusService.incrementFailedFiles();
                        notificationService.addNotification("Erreur lors du traitement du fichier. Message d'erreur: " + e.getMessage());
                    }
                });

            } else {
                logger.warn("Item does not contain '#' and will be skipped: " + item);
            }
        }
    }

    private void processItem(String item, String[] parts) throws IOException {
        String fileType = determineFileType(parts);
        logger.info("Item: " + item);
        logger.info("Parts length: " + parts.length);
        logger.info("Determined file type: " + fileType);

        if (fileType != null) {
            String fileName = generateFileName(fileType);
            logger.info("Generated file name: " + fileName);

            processFile(item, fileName);
        } else {
            logger.warn("Unrecognized item format: " + String.join("#", parts));
        }
    }

    private String determineFileType(String[] parts) {
        if (parts.length == 4 && parts[0].matches("BE\\d{1,9}")) return "EtatImpayes";
        if (parts.length == 6) return "EtatReglementPrime";
        if (parts.length == 5 && parts[1].matches("\\w+")) return "ChangementDebiteur";
        if (parts.length == 7 && parts[3].matches("\\d{2}/\\d{2}/\\d{4}")) return "DemandeMEJGarantie";
        if (parts.length == 33) return "DemandeGarantieFOG";
        if (parts.length == 2) return "EtatAnnulationMEJ";
        if (parts.length == 5 && parts[1].matches("\\d{2}/\\d{2}/\\d{4}")) return "DetailReglementRistourne";
        if (parts.length == 7 && parts[5].matches("\\d{2}/\\d{2}/\\d{4}")) return "EtatRecouvrementRealise";
        if (parts.length == 4 && parts[1].matches("\\d+(,\\d{2})?")) return "RestitutionMEJ";
        if (parts.length == 4 && parts[1].matches("\\d{2}/\\d{2}/\\d{4}")) return "ReglementMEJ";
        return null;
    }

    private boolean isScheduledTime(LocalDateTime now) {
        if (scheduledDateTime == null) return false;

        LocalDateTime startTime = scheduledDateTime.minusSeconds(TOLERANCE_SECONDS);
        LocalDateTime endTime = scheduledDateTime.plusSeconds(TOLERANCE_SECONDS);
        return !now.isBefore(startTime) && !now.isAfter(endTime);
    }

    private void processFile(String item, String fileName) throws IOException {
        synchronized (fileLocks.computeIfAbsent(fileName, k -> new Object())) {
            File file = new File(DIRECTORY_PATH + fileName);
            boolean isFileNew = !file.exists() || file.length() == 0;

            try (FileWriter fileWriter = new FileWriter(file, true)) {
                if (isFileNew) {
                    String header = determineHeader(fileName);
                    if (header != null) {
                        fileWriter.write(header + System.lineSeparator());
                        logger.info("Header written to file: " + fileName);
                    }
                }
                fileWriter.write(item + System.lineSeparator());
                logger.info("Item written to file: " + fileName);
            } catch (IOException e) {
                logger.error("Error writing to file: " + fileName, e);
                throw e;
            }
        }
    }

    private String determineHeader(String fileName) {
        if (fileName.contains("EtatImpayes")) {
            return "numCIN#numCredit#dateImpaye#principalImpaye";
        } else if (fileName.contains("EtatReglementPrime")) {
            return "numCIN#numCredit#dateEcheance#montantRegle#refReglement#dateReglement";
        } else if (fileName.contains("ChangementDebiteur")) {
            return "numCredit#numCIN#debiteurInit#nouveauDebit#dateEffetTransfert";
        } else if (fileName.contains("DemandeMEJGarantie")) {
            return "numCIN#numCredit#montant#dateEcheance#datePremEchImpaye#montantRestant#montantReclame";
        } else if (fileName.contains("DemandeGarantieFOG")) {
            return "nom#prenom#numCIN#sexe#dateNaissance#profession#numCreditBq#montant#duree#quotiteFinancement#objetCredit#tauxInteret#tauxInteretRetard#coutGlobal#prix#superficie#codeVille#numTitreFoncier#fraisCapitale#typeLogement#revenuMensuel#marie#revenuConjoint#nbrPrsnCharge#ancienneteBancaire#adresseLogmeent#vendeurLogemet#differe#aquisitionIndivision#typePrime#prixTerrain#natureTF#paysAccueil";
        } else if (fileName.contains("EtatAnnulationMEJ")) {
            return "idCredit#codeMotif";
        } else if (fileName.contains("DetailReglementRistourne")) {
            return "idCredit#dateEcheance#montantRistoune#dateReglement#refReglement";
        } else if (fileName.contains("EtatRecouvrementRealise")) {
            return "idCredit#recouvrementRealise#montantFrais#partTamwil#dateRecouvrementBq#dateVirementTamwil#refReglement";
        } else if (fileName.contains("RestitutionMEJ")) {
            return "idCredit#dateRestitution#montantRest#refRestitution";
        } else if (fileName.contains("ReglementMEJ")) {
            return "idCredit#montantMEJ#dateReglement#refReglement";
        }
        return null;
    }

    private String generateFileName(String fileType) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmddMMyy"));
        return fileType + "-" + timestamp + ".txt";
    }
}
