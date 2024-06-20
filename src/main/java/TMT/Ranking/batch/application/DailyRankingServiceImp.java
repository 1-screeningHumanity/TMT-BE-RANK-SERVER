package TMT.Ranking.batch.application;

import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

    private final DailyWalletRepository dailyWalletInfoRepository;
    private final DailyRankingRepository dailyRankingRepository;



//            //chunk의 타입이 List<?extends> 이기때문
//            List<? extends DailyRankingDto> itemList = items.getItems();
//            itemList.sort(Comparator.comparing(DailyRankingDto::getProfit).reversed());
//            Long rank = 0L;


//    @Override
//    public DailyRanking createprofit(DailyWallet dailyWallet) {
//        return null;
//    }
}
