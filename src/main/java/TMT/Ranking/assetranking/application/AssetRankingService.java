package TMT.Ranking.assetranking.application;

import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.assetranking.vo.MyAssetRankingResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssetRankingService {

    void updateAssetRanking();

    void updateRankingChange();

    void updateYesterdayRanking();

//    List<AssetRankingResponseVo> getAssetRanking();

    // 자산순위 반환
    Page<AssetRankingResponseVo> getAssetRanking(Pageable pageable);

    MyAssetRankingResponseVo getMyAssetRanking(String uuid);

}
