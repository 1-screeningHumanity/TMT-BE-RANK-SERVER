package TMT.Ranking.daliywallet.infrastructure;

import TMT.Ranking.daliywallet.domain.DailyWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWalletInfoRepository extends JpaRepository<DailyWallet, Long> {

}
