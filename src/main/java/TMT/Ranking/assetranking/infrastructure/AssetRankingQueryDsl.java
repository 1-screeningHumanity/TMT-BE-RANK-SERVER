package TMT.Ranking.assetranking.infrastructure;

import TMT.Ranking.assetranking.dto.AssetRankingDto;
import org.springframework.transaction.annotation.Transactional;

public interface AssetRankingQueryDsl {

    void updateAssetRanking(AssetRankingDto assetRankingDto);

    void updateRanking();

    @Transactional
    void updateRankingChange();

    @Transactional
    void updateYesterdayRanking();
}
