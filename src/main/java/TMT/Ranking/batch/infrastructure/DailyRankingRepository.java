package TMT.Ranking.batch.infrastructure;

import TMT.Ranking.batch.domain.DailyRanking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRankingRepository extends JpaRepository<DailyRanking, Long>{

    boolean existsByUuid(String uuid);

    Optional<DailyRanking> findByUuid(String uuid);

    Optional<DailyRanking> findByNickname(String nickanme);

}
