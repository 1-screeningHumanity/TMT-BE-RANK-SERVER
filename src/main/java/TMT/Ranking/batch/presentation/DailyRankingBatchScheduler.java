package TMT.Ranking.batch.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DailyRankingBatchScheduler {

    private final JobLauncher jobLauncher;
    private final DailyRankingJobConfig dailyRankingJobConfig;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Scheduled(cron = "0 40 16 ? * MON-FRI") //일간 수익률 집계
    public void dailyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) dailyRankingJobConfig.dailyRankingJob(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

    }

    @Scheduled(cron = "0 10 17 ? * FRI") //주간 수익률 집계
    public void weeklyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) dailyRankingJobConfig.weeklyRanking(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
    }

//    @Scheduled(cron = "0 30 16 L * *") 매월말일
    @Scheduled(cron = "0 58 15 ? * FRI") //월간 수익률 집계
    public void monthlyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) dailyRankingJobConfig.monthlyRanking(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

        log.info("start monthlyRankingBatch");
    }

}