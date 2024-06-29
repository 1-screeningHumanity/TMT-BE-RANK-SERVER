package TMT.Ranking.weeklyranking.application;

import TMT.Ranking.weeklyranking.vo.WeeklyMyRankingResponseVo;
import TMT.Ranking.weeklyranking.vo.WeeklyRankingResponseVo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WeeklyRankingService {

    void createWeeklyRanking();

    void updateChangeWeeklyRanking();

    void updateLastWeekRanking();

    Page<WeeklyRankingResponseVo> getWeeklyRanking(Pageable pageable);


    WeeklyMyRankingResponseVo getMyWeeklyRanking(String uuid);
}
