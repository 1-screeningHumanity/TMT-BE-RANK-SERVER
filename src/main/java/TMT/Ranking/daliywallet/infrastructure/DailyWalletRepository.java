package TMT.Ranking.daliywallet.infrastructure;

import TMT.Ranking.daliywallet.domain.DailyWallet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWalletRepository extends JpaRepository<DailyWallet, Long> {
    boolean existsByUuid(String uuid);

    List<DailyWallet> findAll();


}
