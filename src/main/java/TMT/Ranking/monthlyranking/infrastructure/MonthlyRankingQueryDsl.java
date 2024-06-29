package TMT.Ranking.monthlyranking.infrastructure;

import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface MonthlyRankingQueryDsl {

    void monthlyRankingCreate(MonthlyRankingDto monthlyRankingDto);

    void monthlyRankingUpdate();

    void updateMonthlyRankingChange();

    void updateLastMonthRanking();

    List<Tuple> getMonthlyRanking(Pageable pageable);

    long getMonthlyRankingCount();
}
