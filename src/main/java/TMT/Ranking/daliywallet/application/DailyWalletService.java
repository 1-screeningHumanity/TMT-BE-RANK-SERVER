package TMT.Ranking.daliywallet.application;

import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import org.springframework.scheduling.annotation.Scheduled;

public interface DailyWalletService {
    void walletInfoRequest();

    void saveDailyWallet(DailyWalletInfoResponseDto dailyWalletInfoResponseDto);

    void updateYesterdayWon();

    void updateMondayWon();

    void updateFridayWon();

    void updateLastMonthWon();

    void updateLastMonthEndWon();

}
