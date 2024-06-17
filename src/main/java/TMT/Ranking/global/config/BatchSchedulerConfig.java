package TMT.Ranking.global.config;


import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchSchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job dailyRankingJob;

    @Scheduled(cron = "0  33 21 * * *")
    public void runDailyRankingJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(dailyRankingJob, jobParameters);
        } catch (Exception e) {
           throw  new CustomException(BaseResponseCode.NOY_YET);
        }
    }

}
