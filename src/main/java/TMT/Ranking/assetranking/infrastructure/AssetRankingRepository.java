package TMT.Ranking.assetranking.infrastructure;

import TMT.Ranking.assetranking.domain.AssetRanking;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRankingRepository extends JpaRepository<AssetRanking, Long> {

    boolean existsByUuid (String uuid);

    Optional<AssetRanking> findByUuid (String uuid);

}
