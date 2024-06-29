package TMT.Ranking.assetranking.infrastructure;

import TMT.Ranking.assetranking.dto.AssetRankingDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AssetRankingQueryDsl {

    void updateAssetRanking(AssetRankingDto assetRankingDto);

    void updateRanking();

    void updateRankingChange();

    void updateYesterdayRanking();

    List<Tuple> getAssetRanking(Pageable pageable);

    long getAssetRankingCount();

}
