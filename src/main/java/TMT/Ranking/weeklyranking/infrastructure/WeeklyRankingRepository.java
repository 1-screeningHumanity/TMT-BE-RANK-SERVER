package TMT.Ranking.weeklyranking.infrastructure;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.weeklyranking.domain.WeeklyRanking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyRankingRepository extends JpaRepository<WeeklyRanking, Long> {

    boolean existsByUuid(String uuid);

    Optional<WeeklyRanking> findByUuid(String uuid);

}
