package TMT.Ranking.monthlyranking.infrastructure;

import TMT.Ranking.monthlyranking.domain.MonthlyRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRankingRepository extends JpaRepository<MonthlyRanking, Long> {

    boolean existsByUuid(String uuid);


}
