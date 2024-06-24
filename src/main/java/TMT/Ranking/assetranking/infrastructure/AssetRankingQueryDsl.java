package TMT.Ranking.assetranking.infrastructure;

import TMT.Ranking.assetranking.dto.AssetRankingDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface AssetRankingQueryDsl {

    void updateAssetRanking(AssetRankingDto assetRankingDto);

    void updateRanking();

    @Transactional
    void updateRankingChange();

    @Transactional
    void updateYesterdayRanking();

    List<Tuple> getAssetRanking();
}
