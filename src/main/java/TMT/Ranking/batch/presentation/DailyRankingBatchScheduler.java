package TMT.Ranking.batch.presentation;

import TMT.Ranking.assetranking.presentation.AssetRankingBatchJobConfig;
import TMT.Ranking.monthlyranking.presentation.MonthlyRankingJobConfig;
import TMT.Ranking.weeklyranking.presentation.WeeklyRankingJobConfig;
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
    private final AssetRankingBatchJobConfig assetRankingBatchJobConfig;
    private final WeeklyRankingJobConfig weeklyRankingJobConfig;
    private final MonthlyRankingJobConfig monthlyRankingBatchConfig;


    @Scheduled(cron = "0 20 11 ? * MON-FRI") //일간 수익률 집계

    public void dailyRankingBatchStart()
            throws Exception{

        log.info("start dailyRankingBatch");
        jobLauncher.run((Job) dailyRankingJobConfig.dailyRankingJob(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

    }
    @Scheduled(cron = "0 21 11 ? * MON-FRI") //주간 수익률 집계
    public void weeklyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) weeklyRankingJobConfig.weeklyRanking(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
    }

    @Scheduled(cron = "0 22 11 ? * MON-FRI") //월간 수익률 집계
    public void monthlyRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) monthlyRankingBatchConfig.monthlyRanking(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

        log.info("start monthlyRankingBatch");
    }

    @Scheduled(cron = "0 32 11 ? * MON-FRI") //자산집계
    public void assetRankingBatchStart()
            throws Exception{

        jobLauncher.run((Job) assetRankingBatchJobConfig.assetRankingBatchJob(
                jobRepository,platformTransactionManager), new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

        log.info("assetBatch Start");
    }

}