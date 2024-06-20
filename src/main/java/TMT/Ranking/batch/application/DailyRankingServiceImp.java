package TMT.Ranking.batch.application;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
import com.querydsl.core.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

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

    private ProfitListResponseVo maptoDto (Tuple tuple) { //tuple to dto

        Long won = tuple.get(dailyRanking.won);
        double profit  = tuple.get(dailyRanking.profit);
        String nickname = tuple.get(dailyRanking.nickname);
        Long todayRanking = tuple.get(dailyRanking.todayranking);
        Long changeRanking = tuple.get(dailyRanking.changeRanking);
        return new ProfitListResponseVo(won, profit, nickname, todayRanking, changeRanking);

    }

    @Override  //일일 랭킹 return 
    public List<ProfitListResponseVo> getProfit(){

        List<Tuple> tuples = dailyRankingQueryDslmp.getRanking();
        List<ProfitListResponseVo> profitListResponseVo = tuples.stream()
                .map(this::maptoDto).toList();

        return profitListResponseVo;

    }






}
