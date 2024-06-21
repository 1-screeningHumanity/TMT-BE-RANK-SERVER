package TMT.Ranking.monthlyranking.application;

import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.monthlyranking.vo.MonthlyRankingResponseVo;
import java.util.List;

public interface MonthlyRankingService {

    void updateMonthlyRanking();

    void updateMonthlyRankingChange();

    void updateLastMonthRanking();

    List<MonthlyRankingResponseVo> getMonthlyRanking();

    MonthlyMyRankingResponseVo getMyMonthlyRanking(String uuid);
}
