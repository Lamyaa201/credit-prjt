package net.nak.Schedule;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class MyJobExecutionListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            System.err.println("Job échoué : " + jobExecution.getAllFailureExceptions());
            for (Throwable throwable : jobExecution.getAllFailureExceptions()) {
                throwable.printStackTrace(); // Affiche la trace complète de l'erreur
            }
        }
    }
}
