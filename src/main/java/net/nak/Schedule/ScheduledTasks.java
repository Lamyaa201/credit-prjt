package net.nak.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private final JobLauncher jobLauncher;
    private final Job batchJob;

    @Autowired
    public ScheduledTasks(JobLauncher jobLauncher, Job batchJob) {
        this.jobLauncher = jobLauncher;
        this.batchJob = batchJob;
    }
    @Scheduled(cron = "0 50 10 * * ?")
        public void checkAndRunJob() {
            LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
            logger.info("Attempting to start batch job at scheduled time. Current server time: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            try {
                JobParameters params = new JobParametersBuilder()
                        .addLong("timestamp", System.currentTimeMillis())
                        .addString("scheduledDateTime", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .toJobParameters();
                jobLauncher.run(batchJob, params);
                logger.info("Batch job started successfully.");
            } catch (Exception e) {
                logger.error("Error executing batch job", e);
            }
        }
    }