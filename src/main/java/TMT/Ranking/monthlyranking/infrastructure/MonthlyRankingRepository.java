package TMT.Ranking.monthlyranking.infrastructure;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.monthlyranking.domain.MonthlyRanking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyRankingRepository extends JpaRepository<MonthlyRanking, Long> {

    boolean existsByUuid(String uuid);

    Optional<MonthlyRanking> findByUuid(String uuid);

}
