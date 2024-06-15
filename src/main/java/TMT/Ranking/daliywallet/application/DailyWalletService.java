package TMT.Ranking.daliywallet.application;

import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;

public interface DailyWalletService {
    void walletInfoRequest();

    void saveDailyWallet(DailyWalletInfoResponseDto dailyWalletInfoResponseDto);
}
