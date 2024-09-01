package net.nak.RestControllers;

import net.nak.DTO.*;
import net.nak.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private EtatImpayesService etatImpayesService;

    @Autowired
    private EtatReglementPrimeService etatReglementPrimeService;

    @Autowired
    private ChangementDebiteurService changementDebiteurService;

    @Autowired
    private DemandeMEJGarantieService demandeMEJGarantieService;

    @Autowired
    private DemandeGarantieFOGService demandeGarantieFOGService;

    @Autowired
    private DReglementRistourneService dReglementRistourneService;

    @Autowired
    private EtatAnnulationMejService etatAnnulationMejService;

    @Autowired
    private EtatRecouvrementRealiseService etatRecouvrementRealiseService;

    @Autowired
    private RestitutionMEJService restitutionMEJService;
    @Autowired
    private ReglementMejService reglementMejService;

    @GetMapping("/generateEtatImpayesReport")
    public ResponseEntity<String> generateEtatImpayesReport() {
        try {
            generateEtatImpayesFile();
            return ResponseEntity.ok("Fichier EtatImpayes généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier EtatImpayes !");
        }
    }

    @GetMapping("/generateEtatReglementPrimeReport")
    public ResponseEntity<String> generateEtatReglementPrimeReport() {
        try {
            generateEtatReglementPrimeFile();
            return ResponseEntity.ok("Fichier EtatReglementPrime généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier EtatReglementPrime !");
        }
    }

    @GetMapping("/generateChangementDebiteurReport")
    public ResponseEntity<String> generateChangementDebiteurReport() {
        try {
            generateChangementDebiteurFile();
            return ResponseEntity.ok("Fichier ChangementDebiteur généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier ChangementDebiteur !");
        }
    }

    @GetMapping("/generateDemandeMEJGarantieReport")
    public ResponseEntity<String> generateDemandeMEJGarantieReport() {
        try {
            generateDemandeMEJGarantieFile();
            return ResponseEntity.ok("Fichier DemandeMEJGarantie généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier DemandeMEJGarantie !");
        }
    }

    @GetMapping("/generateDemandeGarantieFOGReport")
    public ResponseEntity<String> generateDemandeGarantieFOGReport() {
        try {
            generateDemandeGarantieFOGFile();
            return ResponseEntity.ok("Fichier Demande Garantie FOG généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Demande Garantie FOG !");
        }
    }
    @GetMapping("/generateDReglementRistourneReport")
    public ResponseEntity<String> generateDReglementRistourneReport() {
        try {
            generateDReglementRistourneFile();
            return ResponseEntity.ok("Fichier Détail Reglement Ristourne généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Détail Reglement Ristourne !");
        }
    }
    @GetMapping("/generateEtatAnnulationMejReport")
    public ResponseEntity<String> generateEtatAnnulationMejReport() {
        try {
            generateEtatAnnulationMejFile();
            return ResponseEntity.ok("Fichier Etat Annulation Mej généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Etat Annulation Mej !");
        }
    }

    @GetMapping("/generateEtatRecouvrementRealiseReport")
    public ResponseEntity<String> generateEtatRecouvrementRealiseReport() {
        try {
            generateEtatRecouvrementRealiseFile();
            return ResponseEntity.ok("Fichier Etat Recouvrement Realise généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Etat Recouvrement Realise !");
        }
    }

    @GetMapping("/generateRestitutionMEJReport")
    public ResponseEntity<String> generateRestitutionMEJReport() {
        try {
            generateRestitutionMEJFile();
            return ResponseEntity.ok("Fichier Restitution MEJ  généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Restitution MEJ !");
        }
    }

    @GetMapping("/generateReglementMejReport")
    public ResponseEntity<String> generateReglementMejReport() {
        try {
            generateReglementMejFile();
            return ResponseEntity.ok("Fichier Reglement Mej généré avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de la génération du fichier Reglement MEJ !");
        }
    }

    private void generateEtatImpayesFile() throws IOException {
        List<EtatImpayesDTO> etatImpayesList = etatImpayesService.getAllEtatImpayes();
        String fileName = generateFileName("EtatImpayes");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("numCIN#numCredit#dateImpaye#principalImpaye");
                writer.newLine();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (EtatImpayesDTO etat : etatImpayesList) {
                if (Boolean.TRUE.equals(etat.getIsActive())) {
                    String numCIN = etat.getNumCIN() != null ? etat.getNumCIN() : "DEFAULT_NUM_CIN";
                    String numCredit = etat.getNumCredit() != null ? etat.getNumCredit() : "DEFAULT_NUM_CREDIT";

                    String dateImpaye;
                    if (etat.getDateImpaye() != null) {
                        LocalDate localDate = etat.getDateImpaye().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        dateImpaye = localDate.format(dateFormatter);
                    } else {
                        dateImpaye = "01-01-1970";
                    }

                    String principalImpaye = etat.getPrincipalImpaye() != null ? String.format("%.2f", etat.getPrincipalImpaye()) : "0.00";
                    String line = String.format("%s#%s#%s#%s", numCIN, numCredit, dateImpaye, principalImpaye);

                    if (!existingEntries.contains(line)) {
                        writer.write(line);
                        writer.newLine();
                        existingEntries.add(line);
                    }
                }
            }
        }
    }

    private void generateEtatReglementPrimeFile() throws IOException {
        List<EtatReglementPrimeDTO> etatReglementPrimeList = etatReglementPrimeService.getAllEtatReglementPrime();
        String fileName = generateFileName("EtatReglementPrime");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("numCIN#numCredit#dateEcheance#montantRegle#refReglement#dateReglement");
                writer.newLine();
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (EtatReglementPrimeDTO etat : etatReglementPrimeList) {
                String numCIN = etat.getNumCin() != null ? etat.getNumCin() : "DEFAULT_NUM_CIN";
                String numCredit = etat.getNumCredit() != null ? etat.getNumCredit() : "DEFAULT_NUM_CREDIT";

                String dateEcheance;
                if (etat.getDateEcheance() != null) {
                    LocalDate localDate = etat.getDateEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateEcheance = localDate.format(dateFormatter);
                } else {
                    dateEcheance = "01-01-1970";
                }

                String montantRegle = etat.getMontantRegle() != null ? String.format("%.2f", etat.getMontantRegle()) : "0.00";
                String refReglement = etat.getRefReglement() != null ? etat.getRefReglement() : "DEFAULT_REF";
                String dateReglement;
                if (etat.getDateReglement() != null) {
                    LocalDate localDate = etat.getDateReglement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateReglement = localDate.format(dateFormatter);
                } else {
                    dateReglement = "01-01-1970";
                }

                String line = String.format("%s#%s#%s#%s#%s#%s", numCIN, numCredit, dateEcheance, montantRegle, refReglement, dateReglement);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }

    private void generateChangementDebiteurFile() throws IOException {
        List<ChangementDebiteurDTO> changementDebiteurList = changementDebiteurService.getAllChangementDebiteur();
        String fileName = generateFileName("ChangementDebiteur");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("numCIN#numCredit#debiteurInit#nouveauDebit#dateEffetTransfert");
                writer.newLine();
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (ChangementDebiteurDTO changement : changementDebiteurList) {
                String numCIN = changement.getNumCIN() != null ? changement.getNumCIN() : "DEFAULT_NUM_CIN";
                String numCredit = changement.getNumCredit() != null ? changement.getNumCredit() : "DEFAULT_NUM_CREDIT";

                // Utilisation de getLabel() pour obtenir la chaîne descriptive des valeurs d'énumération
                String debiteurInit = changement.getDebiteurInit() != null ? changement.getDebiteurInit().getLabel() : "DEFAULT_DEBITEUR_INIT";
                String nouveauDebit = changement.getNouveauDebit() != null ? changement.getNouveauDebit().getLabel() : "DEFAULT_NOUVEAU_DEBIT";

                String dateEffetTransfert;
                if (changement.getDateEffetTransfert() != null) {
                    LocalDate localDate = changement.getDateEffetTransfert().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateEffetTransfert = localDate.format(dateFormatter);
                } else {
                    dateEffetTransfert = "01-01-1970";
                }

                String line = String.format("%s#%s#%s#%s#%s", numCIN, numCredit, debiteurInit, nouveauDebit, dateEffetTransfert);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }

    private void generateDemandeMEJGarantieFile() throws IOException {
        List<DemandeMEJGarantieDTO> demandeMEJGarantieList = demandeMEJGarantieService.getAllDemandeMEJGarantie();
        String fileName = generateFileName("DemandeMEJGarantie");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("numCIN#numCredit#montant#dateEcheance#datePremEchImpaye#montantRestant#montantReclame");
                writer.newLine();
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (DemandeMEJGarantieDTO demande : demandeMEJGarantieList) {
                String numCIN = demande.getNumCIN() != null ? demande.getNumCIN() : "DEFAULT_NUM_CIN";
                String numCredit = demande.getNumCredit() != null ? demande.getNumCredit() : "DEFAULT_NUM_CREDIT";
                String dateEcheance;
                if (demande.getDateEcheance() != null) {
                    LocalDate localDate = demande.getDateEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateEcheance = localDate.format(dateFormatter);
                } else {
                    dateEcheance = "01-01-1970"; }

                String datePremEchImpaye;
                if (demande.getDatePremEchImpaye() != null) {
                    LocalDate localDate = demande.getDatePremEchImpaye().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    datePremEchImpaye = localDate.format(dateFormatter);
                } else {
                    datePremEchImpaye = "01-01-1970"; }
                String montant = demande.getMontant() != null ? String.format("%.2f", demande.getMontant()) : "0.00";
                String montantRestant = demande.getMontantRestant() != null ? String.format("%.2f", demande.getMontantRestant()) : "0.00";
                String montantReclame = demande.getMontantReclame() != null ? String.format("%.2f", demande.getMontantReclame()) : "0.00";

                String line = String.format("%s#%s#%s#%s#%s#%s#%s", numCIN, numCredit, montant, dateEcheance, datePremEchImpaye, montantRestant, montantReclame);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }
    private void generateDemandeGarantieFOGFile() throws IOException {
        List<DemandeGarantieFOGDTO> demandeMEJGarantieList = demandeGarantieFOGService.getAllDemandeGarantieFOG();
        String fileName = generateFileName("DemandeGarantieFOG");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("nom#prenom#numCIN#sexe#dateNaissance#profession#numCreditBq#montant#duree#quotiteFinancement#objetCredit#tauxInteret#tauxInteretRetard#coutGlobal#prix#superficie#codeVille#numTitreFoncier#fraisCapitale#typeLogement#revenuMensuel#marie#revenuConjoint#nbrPrsnCharge#ancienneteBancaire#adresseLogmeent#vendeurLogemet#differe#aquisitionIndivision#typePrime#prixTerrain#natureTF#paysAccueil");
                writer.newLine();
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (DemandeGarantieFOGDTO demande : demandeMEJGarantieList) {
                String nom = demande.getNom() != null ? demande.getNom() : "DEFAULT_NOM";
                String prenom = demande.getPrenom() != null ? demande.getPrenom() : "DEFAULT_PRENOM";
                String numCIN = demande.getNumCIN() != null ? demande.getNumCIN() : "DEFAULT_NUM_CIN";
                String sexe = demande.getSexe() != null ? demande.getSexe().getLabel() : "DEFAULT_SEXE";
                String dateNaissance;
                if (demande.getDateNaissance() != null) {
                    LocalDate localDate = demande.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateNaissance = localDate.format(dateFormatter);
                } else {
                    dateNaissance = "01-01-1970"; }
                String profession = demande.getProfession() != null ? demande.getProfession() : "DEFAULT_PROFESSION";
                String numCreditBq = demande.getNumCreditBq() != null ? demande.getNumCreditBq() : "DEFAULT_NUM_CREDIT_BQ";
                String montant = demande.getMontant() != null ? String.format("%.2f", demande.getMontant()) : "0.00";
                String duree = demande.getDuree() != null ? demande.getDuree() : "DEFAULT_DUREE";
                String quotiteFinancement = demande.getQuotiteFinancement() != null ? String.format("%.2f", demande.getQuotiteFinancement()) : "0.00";
                String objetCredit = demande.getObjetCredit() != null ? demande.getObjetCredit().getLabel() : "DEFAULT_OBJET_CREDIT";
                String tauxInteret = demande.getTauxInteret() != null ? String.format("%.2f", demande.getTauxInteret()) : "0.00";
                String tauxInteretRetard = demande.getTauxInteretRetard() != null ? String.format("%.2f", demande.getTauxInteretRetard()) : "0.00";
                String coutGlobal = demande.getCoutGlobal() != null ? String.format("%.2f", demande.getCoutGlobal()) : "0.00";
                String prix = demande.getPrix() != null ? String.format("%.2f", demande.getPrix()) : "0.00";
                String superficie = demande.getSuperficie() != null ? demande.getSuperficie() : "DEFAULT_SUPERFICIE";
                String codeVille = demande.getCodeVille() != null ? demande.getCodeVille().toString() : "DEFAULT_CODE_VILLE";
                String numTitreFoncier = demande.getNumTitreFoncier() != null ? demande.getNumTitreFoncier() : "DEFAULT_NUM_TITRE_FONCIER";
                String fraisCapitale = demande.getFraisCapitale() != null ? String.format("%.2f", demande.getFraisCapitale()) : "0.00";
                String typeLogement = demande.getTypeLogement() != null ? demande.getTypeLogement().getLabel() : "DEFAULT_TYPE_LOGEMENT";
                String revenuMensuel = demande.getRevenuMensuel() != null ? demande.getRevenuMensuel().getLabel() : "DEFAULT_REVENU_MENSUEL";
                String marie = demande.getMarie() != null ? demande.getMarie().toString() : "DEFAULT_MARIE";
                String revenuConjoint = demande.getRevenuConjoint() != null ? String.format("%.2f", demande.getRevenuConjoint()) : "0.00";
                String nbrPrsnCharge = demande.getNbrPrsnCharge() != null ? String.format("%.2f", demande.getNbrPrsnCharge()) : "0.00";
                String ancienneteBancaire = demande.getAncienneteBancaire() != null ? demande.getAncienneteBancaire() : "DEFAULT_ANCINETE_BANCAIRE";
                String adresseLogmeent = demande.getAdresseLogmeent() != null ? demande.getAdresseLogmeent() : "DEFAULT_ADRESSE_LOGEMENT";
                String vendeurLogemet = demande.getVendeurLogemet() != null ? demande.getVendeurLogemet() : "DEFAULT_VENDEUR_LOGEMENT";
                String differe = demande.getDiffere() != null ? demande.getDiffere().toString() : "DEFAULT_DIFFERE";
                String aquisitionIndivision = demande.getAquisitionIndivision() != null ? demande.getAquisitionIndivision().getLabel() : "DEFAULT_ACQUISITION_INDIVISION";
                String typePrime = demande.getTypePrime() != null ? demande.getTypePrime().getLabel() : "DEFAULT_TYPE_PRIME";
                String prixTerrain = demande.getPrixTerrain() != null ? String.format("%.2f", demande.getPrixTerrain()) : "0.00";
                String natureTF = demande.getNatureTF() != null ? demande.getNatureTF().getLabel() : "DEFAULT_NATURE_TF";
                String paysAccueil = demande.getPaysAccueil() != null ? demande.getPaysAccueil() : "DEFAULT_PAYS_ACCUEIL";

                String line = String.join("#", nom, prenom, numCIN, sexe, dateNaissance, profession, numCreditBq, montant, duree, quotiteFinancement, objetCredit, tauxInteret, tauxInteretRetard, coutGlobal, prix, superficie, codeVille, numTitreFoncier, fraisCapitale, typeLogement, revenuMensuel, marie, revenuConjoint, nbrPrsnCharge, ancienneteBancaire, adresseLogmeent, vendeurLogemet, differe, aquisitionIndivision, typePrime, prixTerrain, natureTF, paysAccueil);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }

    private void generateDReglementRistourneFile() throws IOException {
        List<DetailReglementRistourneDTO> dReglementRistourneDTOList = dReglementRistourneService.getAllDetailReglementRistourne();
        String fileName = generateFileName("DétailReglementRistourne");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("idCredit#dateEcheance#montantRistoune#dateReglement#refReglement");
                writer.newLine();
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (DetailReglementRistourneDTO dReglement : dReglementRistourneDTOList) {
                if (Boolean.TRUE.equals(dReglement.getIsActive())) {
                    String idCredit = dReglement.getIdCredit() != null ? dReglement.getIdCredit() : "DEFAULT_ID_CREDIT";
                    String dateEcheance;
                    if (dReglement.getDateEcheance() != null) {
                        LocalDate localDate = dReglement.getDateEcheance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        dateEcheance = localDate.format(dateFormatter);
                    } else {
                        dateEcheance = "01-01-1970"; }
                    String montantRistoune = dReglement.getMontantRistoune() != null ? String.format("%.2f", dReglement.getMontantRistoune()) : "0.00";

                    String dateReglement;
                    if (dReglement.getDateReglement() != null) {
                        LocalDate localDate = dReglement.getDateReglement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        dateReglement = localDate.format(dateFormatter);
                    } else {
                        dateReglement = "01-01-1970"; }
                    String refReglement = dReglement.getRefReglement() != null ? dReglement.getRefReglement() : "DEFAULT_REF_REGLEMENT";

                    String line = String.format("%s#%s#%s#%s#%s", idCredit, dateEcheance, montantRistoune,dateReglement,refReglement);

                    if (!existingEntries.contains(line)) {
                        writer.write(line);
                        writer.newLine();
                        existingEntries.add(line);
                    }
                }
            }
        }
    }

    private void generateEtatAnnulationMejFile() throws IOException {
        List<EtatAnnulationMejDTO> etatAnnulationMejDTOList = etatAnnulationMejService.getAllEtatAnnulationMej();
        String fileName = generateFileName("EtatAnnulationMej");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("idCredit#codeMotif");
                writer.newLine();
            }
            for (EtatAnnulationMejDTO etatAnnulation : etatAnnulationMejDTOList) {
                String ideCredit = etatAnnulation.getIdCredit() != null ? etatAnnulation.getIdCredit() : "DEFAULT_ID_CREDIT";
                String codeMotif = etatAnnulation.getCodeMotif() != null ? etatAnnulation.getCodeMotif().getLabel() : "DEFAULT_CODE_MOTIF";
                String line = String.format("%s#%s", ideCredit, codeMotif);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }

    private void generateEtatRecouvrementRealiseFile() throws IOException {
        List<EtatRecouvrementRealiseDTO> etatRecouvrementRealiseDTOList = etatRecouvrementRealiseService.getAllEtatRecouvrementRealise();
        String fileName = generateFileName("EtatRecouvrementRealise");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("idCredit#codeMotif");
                writer.newLine();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            for (EtatRecouvrementRealiseDTO etatRecouvrementR : etatRecouvrementRealiseDTOList) {
                String idCredit = etatRecouvrementR.getIdCredit() != null ? etatRecouvrementR.getIdCredit() : "DEFAULT_ID_CREDIT";
                String recouvrementRealise = etatRecouvrementR.getRecouvrementRealise() != null ? String.format("%.2f", etatRecouvrementR.getRecouvrementRealise()) : "0.00";
                String montantFrais = etatRecouvrementR.getMontantFrais() != null ? String.format("%.2f", etatRecouvrementR.getMontantFrais()) : "0.00";
                String partTamwil = etatRecouvrementR.getPartTamwil() != null ? String.format("%.2f", etatRecouvrementR.getPartTamwil()) : "0.00";
                String dateRecouvrementBq;
                if (etatRecouvrementR.getDateRecouvrementBq() != null) {
                    LocalDate localDate = etatRecouvrementR.getDateRecouvrementBq().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateRecouvrementBq = localDate.format(dateFormatter);
                } else {
                    dateRecouvrementBq = "01-01-1970"; }
                String dateVirementTamwil;
                if (etatRecouvrementR.getDateVirementTamwil() != null) {
                    LocalDate localDate = etatRecouvrementR.getDateVirementTamwil().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateVirementTamwil = localDate.format(dateFormatter);
                } else {
                    dateVirementTamwil = "01-01-1970"; }
                String refReglement = etatRecouvrementR.getRefReglement() != null ? etatRecouvrementR.getRefReglement() : "DEFAULT_REF_REGLEMENT";

                String line = String.format("%s#%s#%s#%s#%s#%s#%s", idCredit, recouvrementRealise, montantFrais, partTamwil, dateRecouvrementBq, dateVirementTamwil, refReglement);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }

    private void generateRestitutionMEJFile() throws IOException {
        List<RestitutionMEJDTO> restitutionMEJDTOList = restitutionMEJService.getAllRestitutionMEJ();
        String fileName = generateFileName("RestitutionMEJ");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("idCredit#montantRest#dateRestitution#refRestitution");
                writer.newLine();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (RestitutionMEJDTO restitutionM : restitutionMEJDTOList) {
                String ideCredit = restitutionM.getIdCredit() != null ? restitutionM.getIdCredit() : "DEFAULT_ID_CREDIT";
                String montantRest = restitutionM.getMontantRest() != null ? String.format("%.2f", restitutionM.getMontantRest()) : "0.00";
                String dateRestitution;
                if (restitutionM.getDateRestitution() != null) {
                    LocalDate localDate = restitutionM.getDateRestitution().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateRestitution = localDate.format(dateFormatter);
                } else {
                    dateRestitution = "01-01-1970"; }
                String refRestitution = restitutionM.getRefRestitution() != null ? restitutionM.getRefRestitution() : "DEFAULT_REF_RESTITUTION";

                String line = String.format("%s#%s#%s#%s", ideCredit, montantRest, dateRestitution, refRestitution);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }
    private void generateReglementMejFile() throws IOException {
        List<ReglementMejDTO> reglementMejDTOList = reglementMejService.getAllReglementMej();
        String fileName = generateFileName("ReglementMej");
        Set<String> existingEntries = readExistingEntries(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Repertoire-stock/" + fileName, true))) {
            if (Files.size(Paths.get("C:/Repertoire-stock/" + fileName)) == 0) {
                writer.write("idCredit#montantMEJ#dateReglement#refReglement");
                writer.newLine();
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (ReglementMejDTO reglementM: reglementMejDTOList) {
                String ideCredit = reglementM.getIdCredit() != null ? reglementM.getIdCredit() : "DEFAULT_ID_CREDIT";
                String montantMEJ = reglementM.getMontantMEJ() != null ? String.format("%.2f", reglementM.getMontantMEJ()) : "0.00";
                String dateReglement;
                if (reglementM.getDateReglement() != null) {
                    LocalDate localDate = reglementM.getDateReglement().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    dateReglement = localDate.format(dateFormatter);
                } else {
                    dateReglement = "01-01-1970"; }
                String refReglement = reglementM.getRefReglement() != null ? reglementM.getRefReglement() : "DEFAULT_REF_REGLEMENT";

                String line = String.format("%s#%s#%s#%s", ideCredit, montantMEJ, dateReglement, refReglement);

                if (!existingEntries.contains(line)) {
                    writer.write(line);
                    writer.newLine();
                    existingEntries.add(line);
                }
            }
        }
    }
    private String generateFileName(String reportType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmddMMyy");
        String timestamp = LocalDateTime.now().format(formatter);
        return reportType + "_" + timestamp + ".txt";
    }

    private Set<String> readExistingEntries(String fileName) throws IOException {
        Set<String> entries = new HashSet<>();
        Path filePath = Paths.get("C:/Repertoire-stock/" + fileName);

        if (Files.exists(filePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    entries.add(line);
                }
            }
        }
        return entries;
    }
}