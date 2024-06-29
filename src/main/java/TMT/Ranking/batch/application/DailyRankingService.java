package TMT.Ranking.batch.application;

import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.batch.vo.MemberDailyRankingResponseVo;
import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

public interface DailyRankingService {


    void createRank();

    void updateYesterdayRanking();
    void updateChangeRanking();

    //일일 랭킹 return
    Page<ProfitListResponseVo> getProfit(Pageable pageable);

    MyProfitResponseVo getMyProfit(String uuid);

    MemberDailyRankingResponseVo getMemberDailyRanking(String nickname);
}
