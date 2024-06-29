package TMT.Ranking.monthlyranking.application;

import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.monthlyranking.vo.MonthlyRankingResponseVo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonthlyRankingService {

    void updateMonthlyRanking();

    void updateMonthlyRankingChange();

    void updateLastMonthRanking();

    Page<MonthlyRankingResponseVo> getMonthlyRanking(Pageable pageable);

    MonthlyMyRankingResponseVo getMyMonthlyRanking(String uuid);
}
