package TMT.Ranking.batch.application;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

    private final DailyWalletInfoRepository dailyWalletInfoRepository;

    @Override
    public DailyRanking createprofit(DailyWallet dailyWallet){

        LocalDate today = dailyWallet.getCreatedAt().toLocalDate();
        LocalDate yesterday = today.minusDays(1);

        DailyWallet yesterdayWallet = dailyWalletInfoRepository.findByUuidAndCreatedAt(
                dailyWallet.getUuid(), yesterday.atStartOfDay());
        if(yesterdayWallet ==null){
            throw new CustomException(BaseResponseCode.EMPTY_YESTERDAYWALLET);
        }
        double profit = ((dailyWallet.getWon() - yesterdayWallet.getWon())
                /(double)yesterdayWallet.getWon())*100;

        return DailyRanking.builder()
                .uuid(dailyWallet.getUuid())
                .won(dailyWallet.getWon())
                .profit(profit)
                .build();

    }
}
