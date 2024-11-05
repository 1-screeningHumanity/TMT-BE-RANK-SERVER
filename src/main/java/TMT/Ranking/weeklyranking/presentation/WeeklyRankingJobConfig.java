package TMT.Ranking.weeklyranking.presentation;

import TMT.Ranking.assetranking.presentation.CustomSkipListner;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import TMT.Ranking.weeklyranking.domain.WeeklyRanking;
import TMT.Ranking.weeklyranking.dto.WeeklyRankingDto;
import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingQueryDslImp;
import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;


@RequiredArgsConstructor
@Slf4j
@Configuration
public class WeeklyRankingJobConfig {

    private final WeeklyRankingRepository weeklyRankingRepository;
    private final WeeklyRankingQueryDslImp weeklyRankingQueryDslImp;
    private final DailyWalletRepository dailyWalletRepository;

    @Autowired
    private CustomSkipListner customSkipListner;


    @Bean //주간랭킹 집계
    public Job weeklyRanking(JobRepository jobRepository,
            PlatformTransactionManager transactionManager){

        return new JobBuilder("weeklyRanking", jobRepository)
                .start(weeklyRankingStep(jobRepository, transactionManager))
                .build();

    }
    @Bean //주간랭킹 집계 Step
    public Step weeklyRankingStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager){

        return new StepBuilder("weeklyRankingStep", jobRepository)
                .<DailyWallet, WeeklyRankingDto>chunk(10, transactionManager)
                .reader(weeklyRankingReader())
                .processor(weeklyRankingProcessor())
                .writer(weeklyRankingWriter())
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(100)
                .retry(Exception.class)
                .retryLimit(3)
                .listener(customSkipListner)
                .build();g
    }

    @Bean
    public ItemReader<DailyWallet> weeklyRankingReader() {
        return new RepositoryItemReaderBuilder<DailyWallet>()
                .name("readWalletInfo")
                .repository(dailyWalletRepository)
                .methodName("findAll")
                .pageSize(10)
                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
                .build();
    }

    @Bean
    public ItemProcessor<DailyWallet, WeeklyRankingDto> weeklyRankingProcessor(){

        return dailyWallet -> {
            if (dailyWallet.getLastMondayWon() == null && dailyWallet.getFridayWon() == null) {
                throw new CustomException(BaseResponseCode.NO_DATA);
            }
            double profit = ((double) (dailyWallet.getFridayWon()  //수익률 구하는 연산
                    - dailyWallet.getLastMondayWon()) / dailyWallet.getLastMondayWon()) * 100;

            //수익률 소숫점 3자리로 제한
            BigDecimal roundedProfit =
                    new BigDecimal(profit).setScale(3, RoundingMode.HALF_UP);

            return WeeklyRankingDto
                    .builder()
                    .uuid(dailyWallet.getUuid())
                    .won(dailyWallet.getFridayWon())
                    .profit(roundedProfit.doubleValue())
                    .nickname(dailyWallet.getNickname())
                    .build();
        };

    }

    @Bean
    public ItemWriter<WeeklyRankingDto> weeklyRankingWriter() {
        return items ->{
            for(WeeklyRankingDto item : items){
                if (weeklyRankingRepository.existsByUuid(item.getUuid())) {
                    weeklyRankingQueryDslImp.updateWeeklyRanking(item);
                }else {
                    WeeklyRanking weeklyRanking = WeeklyRanking.builder()
                            .uuid(item.getUuid())
                            .won(item.getWon())
                            .profit(item.getProfit())
                            .nickname(item.getNickname())
                            .build();

                    weeklyRankingRepository.save(weeklyRanking);
                }
                log.info("save weeklyRanking");
            }
        };
    }


}
