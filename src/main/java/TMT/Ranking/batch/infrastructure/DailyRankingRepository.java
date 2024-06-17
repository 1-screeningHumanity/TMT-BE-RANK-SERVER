package TMT.Ranking.batch.infrastructure;

import TMT.Ranking.batch.domain.DailyRanking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRankingRepository extends JpaRepository<DailyRanking, Long> {




}
