package TMT.Ranking.weeklyranking.infrastructure;


import TMT.Ranking.weeklyranking.dto.WeeklyRankingDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface WeeklyRankingQeuryDsl {

    void updateWeeklyRanking(WeeklyRankingDto weeklyRankingDto);

    void createWeeklyRanking();


    void updateChangeWeeklyRanking();


    void updateLastWeekRanking();

    List<Tuple> getWeeklyRanking(Pageable pageable);

    long getWeeklyRankingCount();

}
