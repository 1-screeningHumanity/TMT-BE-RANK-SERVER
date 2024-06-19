package TMT.Ranking.daliywallet.infrastructure;

import org.springframework.transaction.annotation.Transactional;

public interface DailyWalletQueryDslRepository {


    void updateTodayWon(String uuid, Long won);

    void updateMondayWon();

    void updateFridayWon();


    void updateLastMonthWon();


    void updateLastMonthEndWon();

    @Transactional
    void updateYesterdayWon();
}
