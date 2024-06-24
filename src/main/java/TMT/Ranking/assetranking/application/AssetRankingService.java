package TMT.Ranking.assetranking.application;

import org.springframework.scheduling.annotation.Scheduled;

public interface AssetRankingService {

    @Scheduled(cron = "0 25 11 ? * MON-FRI")
    void updateAssetRanking();

    void updateRankingChange();

    void updateYesterdayRanking();
}
