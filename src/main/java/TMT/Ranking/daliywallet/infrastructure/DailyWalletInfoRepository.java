package TMT.Ranking.daliywallet.infrastructure;

import TMT.Ranking.daliywallet.domain.DailyWallet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DailyWalletInfoRepository extends JpaRepository<DailyWallet, Long> {

    List<DailyWallet> findByCreatedAt(LocalDate date);
    @Query("select w from DailyWallet w where w.uuid = :uuid and w.createdAt = :createdAt")
    DailyWallet findByUuidAndCreatedAt(@Param("uuid")String uuid, @Param("createdAt")LocalDateTime createdAt);


}
