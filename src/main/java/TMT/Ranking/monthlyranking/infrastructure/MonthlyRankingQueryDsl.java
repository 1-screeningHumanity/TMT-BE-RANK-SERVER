package TMT.Ranking.monthlyranking.infrastructure;

import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface MonthlyRankingQueryDsl {

    void monthlyRankingCreate(MonthlyRankingDto monthlyRankingDto);

    void monthlyRankingUpdate();

    void updateMonthlyRankingChange();

    @Transactional
    void updateLastMonthRanking();

    List<Tuple> getMonthlyRanking();
}
