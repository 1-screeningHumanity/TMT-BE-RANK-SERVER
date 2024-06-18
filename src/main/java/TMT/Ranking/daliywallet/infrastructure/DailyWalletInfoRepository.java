package TMT.Ranking.daliywallet.infrastructure;

import TMT.Ranking.daliywallet.domain.DailyWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyWalletInfoRepository extends JpaRepository<DailyWallet, Long> {

    boolean existsByUuid(String uuid);

//    List<DailyWallet> findByCreatedAtBetween(LocalDateTime start,LocalDateTime end);
//
//
//    @Query("select w from DailyWallet w where w.uuid = :uuid and w.createdAt = :createdAt")
//    DailyWallet findByUuidAndCreatedAt(@Param("uuid")String uuid, @Param("createdAt")LocalDateTime createdAt);


}
