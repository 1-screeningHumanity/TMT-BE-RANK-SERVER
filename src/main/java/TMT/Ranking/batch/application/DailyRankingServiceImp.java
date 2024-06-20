package TMT.Ranking.batch.application;

import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

    private final DailyWalletRepository dailyWalletInfoRepository;
    private final DailyRankingRepository dailyRankingRepository;
    private final DailyRankingQueryDslmp dailyRankingQueryDslmp;


    @Override
    @Scheduled(cron = "0 40 16 ? * MON-FRI")
    public void createRank(){
        dailyRankingQueryDslmp.updateDailyRank();
    }

    @Override
    @Scheduled(cron = "0 50 16 ? * MON-FRI")
    public void updateYesterdayRanking(){
        dailyRankingQueryDslmp.updateYesterdayRanking();
    }

    @Override
    @Scheduled(cron = "0 0 17 ? * MON-FRI")
    public void updateChangeRanking(){
        dailyRankingQueryDslmp.updateChangeRanking();
    }







//            //chunk의 타입이 List<?extends> 이기때문
//            List<? extends DailyRankingDto> itemList = items.getItems();
//            itemList.sort(Comparator.comparing(DailyRankingDto::getProfit).reversed());
//            Long rank = 0L;


//    @Override
//    public DailyRanking createprofit(DailyWallet dailyWallet) {
//        return null;
//    }
}
