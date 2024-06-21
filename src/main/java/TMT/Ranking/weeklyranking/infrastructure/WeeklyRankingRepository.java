package TMT.Ranking.weeklyranking.infrastructure;

import TMT.Ranking.weeklyranking.domain.WeeklyRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyRankingRepository extends JpaRepository<WeeklyRanking, Long> {

    boolean existsByUuid(String uuid);

}
