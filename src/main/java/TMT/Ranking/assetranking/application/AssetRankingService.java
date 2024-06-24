package TMT.Ranking.assetranking.application;

import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.assetranking.vo.MyAssetRankingResponseVo;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

public interface AssetRankingService {

    void updateAssetRanking();

    void updateRankingChange();

    void updateYesterdayRanking();

    List<AssetRankingResponseVo> getAssetRanking();

    MyAssetRankingResponseVo getMyAssetRanking(String uuid);

}
