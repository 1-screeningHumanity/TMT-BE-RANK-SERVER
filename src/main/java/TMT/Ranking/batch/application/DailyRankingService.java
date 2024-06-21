package TMT.Ranking.batch.application;

import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

public interface DailyRankingService {


    void createRank();

    void updateYesterdayRanking();
    void updateChangeRanking();

    List<ProfitListResponseVo> getProfit();

    MyProfitResponseVo getMyProfit(String uuid);

}
