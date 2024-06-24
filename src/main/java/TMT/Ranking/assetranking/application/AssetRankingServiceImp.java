package TMT.Ranking.assetranking.application;


import TMT.Ranking.assetranking.infrastructure.AssetRankingQueryDslImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetRankingServiceImp implements AssetRankingService {

    private final AssetRankingQueryDslImp assetRankingQueryDslImp;

    @Scheduled(cron = "0 35 11 ? * MON-FRI")
    @Override
    public void updateAssetRanking(){
        log.info("start AssetRanking");
        assetRankingQueryDslImp.updateRanking();
    }

    @Override
    @Scheduled(cron = "0 46 11 ? * MON-FRI")
    public void updateRankingChange(){
        log.info("start updateRankingChange");
        assetRankingQueryDslImp.updateRankingChange();
    }

    @Override
    @Scheduled(cron = "0 49 11 ? * MON-FRI")
    public void updateYesterdayRanking(){
        log.info("start updateYesterdayRanking");
        assetRankingQueryDslImp.updateYesterdayRanking();
    }

}
