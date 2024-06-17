package TMT.Ranking.batch.application;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.daliywallet.domain.DailyWallet;

public interface DailyRankingService {
    DailyRanking createprofit(DailyWallet dailyWallet);
}
