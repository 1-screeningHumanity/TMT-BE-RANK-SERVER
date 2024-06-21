package TMT.Ranking.weeklyranking.infrastructure;


import TMT.Ranking.weeklyranking.dto.WeeklyRankingDto;
import org.springframework.transaction.annotation.Transactional;

public interface WeeklyRankingQeuryDsl {

    void updateWeeklyRanking(WeeklyRankingDto weeklyRankingDto);

    void createWeeklyRanking();


    void updateChangeWeeklyRanking();


    void updateLastWeekRanking();
}
