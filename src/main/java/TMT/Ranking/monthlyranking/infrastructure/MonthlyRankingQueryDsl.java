package TMT.Ranking.monthlyranking.infrastructure;

import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import org.springframework.transaction.annotation.Transactional;

public interface MonthlyRankingQueryDsl {

    void monthlyRankingCreate(MonthlyRankingDto monthlyRankingDto);

    void monthlyRankingUpdate();

    void updateMonthlyRankingChange();

}
