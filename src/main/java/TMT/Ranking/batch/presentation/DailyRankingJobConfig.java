//package TMT.Ranking.batch.presentation;
//
//import TMT.Ranking.batch.application.DailyRankingServiceImp;
//import TMT.Ranking.batch.domain.DailyRanking;
//import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
//import TMT.Ranking.daliywallet.domain.DailyWallet;
//import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.Sort;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//@EnableBatchProcessing
//public class DailyRankingJobConfig {
//
//    private final DailyWalletInfoRepository dailyWalletInfoRepository;
//    private final DailyRankingRepository dailyRankingRepository;
//    private final DailyRankingServiceImp  dailyRankingServiceImp;
//    public LocalDateTime today = LocalDateTime.now();
//    public LocalDateTime yesterday = today.minusDays(1).minusHours(5);
//
//
//
//    @Bean
//    public Job dailyRankingJob(JobRepository jobRepository,
//            PlatformTransactionManager transactionManager) {
//
//        return new JobBuilder("DailyRankingJob", jobRepository)
//                .start(dailyRankingStep(jobRepository, transactionManager))
//                .build();
//
//    }
//
//    @Bean
//    public Step dailyRankingStep(JobRepository jobRepository,
//            PlatformTransactionManager transactionManager) {
//
//        return new StepBuilder("DailyRankingStep", jobRepository)
//                .<DailyWallet, DailyRanking>chunk(10, transactionManager)
//                .reader(yesterdatWalletItemReader())
//                .writer(walletItemWriter())
//                .processor(walletItemProcessor())
//                .writer(walletItemWriter())
//                .build();
//
//    }
//
//    @Bean
//    public ItemReader<DailyWallet> yesterdatWalletItemReader() {
//        return new RepositoryItemReaderBuilder<DailyWallet>()
//                .name("readWalletInfo")
//                .repository(dailyWalletInfoRepository)
//                .methodName("findByCreatedAtBetween")
//                .arguments(today,yesterday)
//                .pageSize(10)
//                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
//                .build();
//    }
//    @Bean //오늘 지갑정보 log 출력용 ItemWriter
//    public ItemWriter<? super DailyRanking> walletItemWriter(){
//        return items -> items.forEach(item ->{
//            log.info("{}", item);
//        });
//    }
//
//    @Bean
//    public ItemProcessor<DailyWallet, DailyRanking> walletItemProcessor() {
//        return dailywalletinfo -> {
//            // Process each wallet here
//            // Example: Calculate profit
//            List<DailyRanking> dailyRankings = new ArrayList<>();
//            for (DailyWallet wallet : dailywalletinfo) {
//                DailyRanking dailyRanking = dailyRankingServiceImp.createprofit(wallet);
//                dailyRankings.add(dailyRanking);
//                log.info("Processed Wallet: {}", wallet);
//            }
//            return dailyRankings;
//        };
//
//
////    @Bean
////    public ItemWriter<DailyRanking> dailyRankingItemWriter(){
////        return items ->{
////            dailyRankingRepository.saveAll(items);
////            items.forEach(item -> log.info("Saved Ranking{}", item));
////        };
////    }
//
//
//}