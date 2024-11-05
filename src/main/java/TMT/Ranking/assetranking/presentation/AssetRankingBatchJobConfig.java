
package TMT.Ranking.assetranking.presentation;

import TMT.Ranking.assetranking.domain.AssetRanking;
import TMT.Ranking.assetranking.dto.AssetRankingDto;
import TMT.Ranking.assetranking.infrastructure.AssetRankingQueryDslImp;
import TMT.Ranking.assetranking.infrastructure.AssetRankingRepository;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class AssetRankingBatchJobConfig {

    private final AssetRankingRepository assetRankingRepository;
    private final AssetRankingQueryDslImp assetRankingQueryDslImp;
    private final DailyWalletRepository dailyWalletRepository;

    @Autowired
    private CustomSkipListner customSkipListner;


    @Bean //일일 자산랭킹 집계
    public Job assetRankingBatchJob(JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager){
        log.info("startJob");
        return new JobBuilder("assetRankingBatchJob", jobRepository)
                .start(assetRankingStep(jobRepository, platformTransactionManager))
                .build();

    }

    @Bean
    public Step assetRankingStep(JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager){
        log.info("startStep");
        return new StepBuilder("assetRankingStep", jobRepository)
                .<DailyWallet, AssetRankingDto>chunk(10, platformTransactionManager)
                .reader(assetRankingItemReader())
                .processor(assetRankingProcessor())
                .writer(assetRankingWriter())
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(100)
                .retry(Exception.class)
                .retryLimit(3)
                .listener(customSkipListner)
                .build();
    }

    @Bean
    public ItemReader<DailyWallet> assetRankingItemReader(){
        log.info("startItemReader");
        return new RepositoryItemReaderBuilder<DailyWallet>()
                .name("readWalletWon")
                .repository(dailyWalletRepository)
                .methodName("findAll")
                .pageSize(10)
                .sorts(Collections.singletonMap("uuid", Sort.Direction.ASC))
                .build();
    }

    @Bean //dailyWallet 에서 오늘 금액을 가져옴
    public ItemProcessor<DailyWallet, AssetRankingDto> assetRankingProcessor(){
        log.info("startItemProcessor");
        return dailyWallet -> {
            if (dailyWallet.getYesterdayWon() == null && dailyWallet.getTodayWon() == null) {
                throw new CustomException(BaseResponseCode.NO_DATA);
            }
            return AssetRankingDto
                    .builder()
                    .uuid(dailyWallet.getUuid())
                    .nickname(dailyWallet.getNickname())
                    .won(dailyWallet.getTodayWon())
                    .build();
        };
    }

    @Bean //이미 있는 회원이면 update 신규회원이면 assetRanking에 저장.
    public ItemWriter<AssetRankingDto> assetRankingWriter(){
        log.info("startItemWriter");
        return items -> {
            for(AssetRankingDto item : items){
                if(assetRankingRepository.existsByUuid(item.getUuid())){
                    assetRankingQueryDslImp.updateAssetRanking(item);
                }else {
                    AssetRanking assetRanking = AssetRanking.builder()
                            .uuid(item.getUuid())
                            .won(item.getWon())
                            .nickname(item.getNickname())
                            .build();
                    assetRankingRepository.save(assetRanking);
                }
                log.info("asserRankingSave");
            }
        };
    }

}



