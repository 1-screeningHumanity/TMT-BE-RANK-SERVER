package TMT.Ranking.weeklyranking.application;

import TMT.Ranking.weeklyranking.vo.WeeklyRankingResponseVo;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;

public interface WeeklyRankingService {

    void createWeeklyRanking();

    void updateChangeWeeklyRanking();

    void updateLastWeekRanking();

    List<WeeklyRankingResponseVo> getWeeklyRanking();

}
