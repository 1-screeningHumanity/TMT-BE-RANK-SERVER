package TMT.Ranking.batch.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class DailyRankingBatchScheduler {

    private final JobLauncher jobLauncher;
    private final DailyRankingJobConfig dailyRankingJobConfig;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Scheduled(cron = "0 40 16 ? * MON-FRI")
    public void dailyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) dailyRankingJobConfig.dailyRankingJob(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

    }

}