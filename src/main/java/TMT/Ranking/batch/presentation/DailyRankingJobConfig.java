package TMT.Ranking.batch.presentation;

import TMT.Ranking.batch.application.DailyRankingServiceImp;
import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
import java.time.LocalDateTime;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class DailyRankingJobConfig {

    private final DailyWalletInfoRepository dailyWalletInfoRepository;
    private final DailyRankingRepository dailyRankingRepository;
    private final DailyRankingServiceImp  dailyRankingServiceImp;


    @Bean
    public Job dailyRankingJob(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {

        return new JobBuilder("DailyRankingJob", jobRepository)
                .start(dailyRankingStep(jobRepository, transactionManager))
                .next(dailyRankingNextStep(jobRepository, transactionManager))
                .build();

    }

    @Bean
    public Step dailyRankingStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("DailyRankingStep", jobRepository)
                .<DailyWallet, DailyRanking>chunk(10, transactionManager)
                .reader(walletItemReader())
                .writer(walletItemWriter())
                .build();

    }

    @Bean
    public Step dailyRankingNextStep(JobRepository jobRepository,
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("DailyRankingNextStep", jobRepository)
                .<DailyWallet, DailyRanking>chunk(10,  transactionManager)
                .reader(walletItemReader())
                .processor(walletItemprocessor())
                .writer(walletItemWriter())
                .build();

    }
    @Bean
    public ItemReader<DailyWallet> walletItemReader() {
        return new RepositoryItemReaderBuilder<DailyWallet>()
                .name("walletItemReader")
                .repository(dailyWalletInfoRepository)
                .methodName("findByCreatedAt")
                .arguments(LocalDateTime.now())
                .pageSize(10)
                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
                .build();
    }
    @Bean //오늘 지갑정보 log 출력용 ItemWriter
    public ItemWriter<? super DailyRanking> walletItemWriter(){
        return items -> items.forEach(item ->{
            log.info("{}", item);
        });
    }

    @Bean
    public ItemProcessor<? super DailyWallet, ? extends DailyRanking> walletItemprocessor(){
        return dailyRankingServiceImp::createprofit; //서비스로직 추가
    }

    @Bean
    public ItemWriter<DailyRanking> dailyRankingItemWriter(){
        return items ->{
            dailyRankingRepository.saveAll(items);
            items.forEach(item -> log.info("Saved Ranking{}", item));
        };
    }
}