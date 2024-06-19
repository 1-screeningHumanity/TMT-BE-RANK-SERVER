package TMT.Ranking.batch.presentation;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.batch.dto.DailyRankingDto;
import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletIQueryDslImp;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class DailyRankingJobConfig {

    private final DailyWalletRepository dailyWalletRepository;
    private final DailyRankingRepository dailyRankingRepository;
    private final DailyRankingQueryDslmp dailyRankingQueryDslmp;

    @Bean
    public Job dailyRankingJob(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {

        return new JobBuilder("DailyRankingJob", jobRepository)
                .start(dailyRankingStep(jobRepository, transactionManager))
                .build();

    }

    @Bean
    public Step dailyRankingStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("DailyRankingStep", jobRepository)
                .<DailyWallet, DailyRankingDto>chunk(10, transactionManager)
                .reader(dailyRankingReader())
                .processor(dailyRankingProcessor())
                .writer(dailyRankingWriter())
                .build();

    }

    @Bean
    public ItemReader<DailyWallet> dailyRankingReader() {
        return new RepositoryItemReaderBuilder<DailyWallet>()
                .name("readWalletInfo")
                .repository(dailyWalletRepository)
                .methodName("findAll")
                .pageSize(10)
                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
                .build();
    }

    @Bean //수익률 구하는 processor
    public ItemProcessor<DailyWallet, DailyRankingDto> dailyRankingProcessor() {
        return dailyWallet -> {
            if (dailyWallet.getYesterdayWon() == null && dailyWallet.getTodayWon() == null) {
                throw new CustomException(BaseResponseCode.NO_DATA);
            }
            double profit = ((double) (dailyWallet.getTodayWon()  //수익률 구하는 연산
                    - dailyWallet.getYesterdayWon()) / dailyWallet.getYesterdayWon()) * 100;

            //수익률 소숫점 3자리로 제한
            BigDecimal roundedProfit =
                    new BigDecimal(profit).setScale(3, RoundingMode.HALF_UP);

            return DailyRankingDto
                    .builder()
                    .uuid(dailyWallet.getUuid())
                    .todayWon(dailyWallet.getTodayWon())
                    .profit(roundedProfit.doubleValue())
                    .build();
        };
    }

    @Bean
    public ItemWriter<DailyRankingDto> dailyRankingWriter() {
        return items -> {
            for (DailyRankingDto item : items) {
                if(dailyRankingRepository.existsByUuid(item.getUuid())){
                    dailyRankingQueryDslmp.updateDailyRanking(
                            item.getUuid(),
                            item.getTodayWon(),
                            item.getProfit());

                }else {
                    DailyRanking dailyRanking = DailyRanking.builder()
                        .uuid(item.getUuid())
                        .won(item.getTodayWon())
                        .profit(item.getProfit())
                        .build();
                        dailyRankingRepository.save(dailyRanking);
                }
            }
            log.info("save dailyRanking");
        };
    }
}