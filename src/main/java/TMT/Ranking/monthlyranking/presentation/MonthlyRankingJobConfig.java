package TMT.Ranking.monthlyranking.presentation;


import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import TMT.Ranking.monthlyranking.domain.MonthlyRanking;
import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingQueryDslImp;
import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingRepository;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class MonthlyRankingJobConfig {

    private final MonthlyRankingRepository monthlyRankingRepository;
    private final MonthlyRankingQueryDslImp monthlyRankingQueryDslImp;
    private final DailyWalletRepository dailyWalletRepository;



    @Bean //월간수익률 집계
    public Job monthlyRanking(JobRepository jobRepository,
            PlatformTransactionManager transactionManager){

        return new JobBuilder("monthlyRanking", jobRepository)
                .start(monthlyRankingStep(jobRepository, transactionManager))
                .build();

    }

    @Bean //월간수익률 집계 Step
    public Step monthlyRankingStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager){

        return new StepBuilder("monthlyRankingStep", jobRepository)
                .<DailyWallet, MonthlyRankingDto>chunk(10, transactionManager)
                .reader(monthlyRankingReader())
                .processor(monthlyRankingProcessor())
                .writer(monthlyRankingWriter())
                .build();
    }

    @Bean //월간수익률 집계 ItemReader
    public ItemReader<DailyWallet> monthlyRankingReader() {
        return new RepositoryItemReaderBuilder<DailyWallet>()
                .name("readWalletInfo")
                .repository(dailyWalletRepository)
                .methodName("findAll")
                .pageSize(10)
                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
                .build();
    }

    @Bean //월간수익률 집계 ItemProcessor
    public ItemProcessor<DailyWallet, MonthlyRankingDto> monthlyRankingProcessor(){

        return dailyWallet -> {
            if (dailyWallet.getLastMonthWon() == null && dailyWallet.getLastMonthEndWon() == null) {
                throw new CustomException(BaseResponseCode.NO_DATA);
            }
            double profit = ((double) (dailyWallet.getLastMonthEndWon()  //수익률 구하는 연산
                    - dailyWallet.getLastMonthWon()) / dailyWallet.getLastMonthWon()) * 100;

            //수익률 소숫점 3자리로 제한
            BigDecimal roundedProfit =
                    new BigDecimal(profit).setScale(3, RoundingMode.HALF_UP);

            return MonthlyRankingDto
                    .builder()
                    .uuid(dailyWallet.getUuid())
                    .won(dailyWallet.getFridayWon())
                    .profit(roundedProfit.doubleValue())
                    .nickname(dailyWallet.getNickname())
                    .build();
        };

    }

    @Bean //월간수익률 ItemReader
    public ItemWriter<MonthlyRankingDto> monthlyRankingWriter() {
        return items ->{
            for(MonthlyRankingDto item : items){
                if (monthlyRankingRepository.existsByUuid(item.getUuid())) {
                    monthlyRankingQueryDslImp.monthlyRankingCreate(item);
                }else {
                    MonthlyRanking monthlyRanking = MonthlyRanking.builder()
                            .uuid(item.getUuid())
                            .won(item.getWon())
                            .profit(item.getProfit())
                            .nickname(item.getNickname())
                            .build();

                    monthlyRankingRepository.save(monthlyRanking);
                }
                log.info("save monthlyRanking");
            }
        };
    }


}
