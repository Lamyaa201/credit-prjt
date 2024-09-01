package net.nak.Schedule;

import net.nak.RestControllers.NotificationController;
import net.nak.repositories.*;
import net.nak.services.FileStatusService;
import net.nak.services.NotificationService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobExecutionListener jobExecutionListener;
    private final EtatImpayesRepository etatImpayesRepository;
    private final ChangementDebiteurRepository changementDebiteurRepository;
    private final DemandeMEJGarantieRepository demandeMEJGarantieRepository;
    private final DemandeGarantieFOGRepository demandeGarantieFOGRepository;
    private final DReglementRistourneRepository dReglementRistourneRepository;
    private final EtatAnnulationMejRepository etatAnnulationMejRepository;
    private final EtatRecouvrementRealiseRepository etatRecouvrementRealiseRepository;
    private final EtatReglementPrimeRepository etatReglementPrimeRepository;
    private final ReglementMejRepository reglementMejRepository;
    private final RestitutionMEJRepository restitutionMEJRepository;
    private final FileStatusService fileStatusService;
    private final NotificationService notificationService;


    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       EtatImpayesRepository etatImpayesRepository,
                       ChangementDebiteurRepository changementDebiteurRepository,
                       DemandeMEJGarantieRepository demandeMEJGarantieRepository,
                       DemandeGarantieFOGRepository demandeGarantieFOGRepository,
                       DReglementRistourneRepository dReglementRistourneRepository,
                       EtatAnnulationMejRepository etatAnnulationMejRepository,
                       EtatRecouvrementRealiseRepository etatRecouvrementRealiseRepository,
                       EtatReglementPrimeRepository etatReglementPrimeRepository,
                       ReglementMejRepository reglementMejRepository,
                       RestitutionMEJRepository restitutionMEJRepository,
                       JobExecutionListener jobExecutionListener,
                       FileStatusService fileStatusService,
                       NotificationService notificationService
    ) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobExecutionListener = jobExecutionListener;
        this.etatImpayesRepository = etatImpayesRepository;
        this.changementDebiteurRepository = changementDebiteurRepository;
        this.demandeMEJGarantieRepository = demandeMEJGarantieRepository;
        this.demandeGarantieFOGRepository = demandeGarantieFOGRepository;
        this.dReglementRistourneRepository = dReglementRistourneRepository;
        this.etatAnnulationMejRepository = etatAnnulationMejRepository;
        this.etatRecouvrementRealiseRepository = etatRecouvrementRealiseRepository;
        this.etatReglementPrimeRepository = etatReglementPrimeRepository;
        this.reglementMejRepository = reglementMejRepository;
        this.restitutionMEJRepository = restitutionMEJRepository;
        this.fileStatusService = fileStatusService;
        this.notificationService = notificationService;

    }

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .flow(step())
                .end()
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Object, String>chunk(10)
                .reader(generalItemReader())
                .processor(generalItemProcessor())
                .writer(generalItemWriter())
                .build();
    }

    @Bean
    public ItemReader<Object> generalItemReader() {
        return new GeneralItemReader(
                etatImpayesRepository,
                changementDebiteurRepository,
                demandeMEJGarantieRepository,
                demandeGarantieFOGRepository,
                dReglementRistourneRepository,
                etatAnnulationMejRepository,
                etatRecouvrementRealiseRepository,
                etatReglementPrimeRepository,
                reglementMejRepository,
                restitutionMEJRepository
        );
    }

    @Bean
    public ItemProcessor<Object, String> generalItemProcessor() {
        return new GeneralItemProcessor();
    }

    @Bean
    public ItemWriter<String> generalItemWriter() {
        return new GeneralItemWriter(fileStatusService, notificationService); // Passez NotificationService ici
    }

}