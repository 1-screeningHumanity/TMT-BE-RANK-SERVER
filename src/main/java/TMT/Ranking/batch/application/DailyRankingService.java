package TMT.Ranking.batch.application;

import TMT.Ranking.batch.vo.ProfitListResponseVo;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

public interface DailyRankingService {


    void createRank();

    void updateYesterdayRanking();

    @Scheduled(cron = "0 0 17 ? * MON-FRI")
    void updateChangeRanking();

    List<ProfitListResponseVo> getProfit();
}
