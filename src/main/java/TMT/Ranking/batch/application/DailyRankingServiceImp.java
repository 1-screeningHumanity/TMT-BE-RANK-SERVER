package TMT.Ranking.batch.application;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import com.querydsl.core.Tuple;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

    private final DailyRankingQueryDslmp dailyRankingQueryDslmp;
    private final DailyRankingRepository dailyRankingRepository;

    @Override
    @Scheduled(cron = "0 23 11 ? * MON-FRI")
    public void createRank(){
        dailyRankingQueryDslmp.updateDailyRank();
    }

    @Override
    @Scheduled(cron = "0 24 11 ? * MON-FRI")
    public void updateYesterdayRanking(){
        dailyRankingQueryDslmp.updateYesterdayRanking();
    }

    @Override
    @Scheduled(cron = "0 25 11 ? * MON-FRI")
    public void updateChangeRanking(){
        dailyRankingQueryDslmp.updateChangeRanking();
    }

    private ProfitListResponseVo maptoDto (Tuple tuple) { //tuple to dto

        double profit  = tuple.get(dailyRanking.profit);
        String nickname = tuple.get(dailyRanking.nickname);
        Long todayRanking = tuple.get(dailyRanking.todayranking);
        Long changeRanking = tuple.get(dailyRanking.changeRanking);
        return new ProfitListResponseVo(profit, nickname, todayRanking, changeRanking);

    }

    @Override  //일일 랭킹 return
    public List<ProfitListResponseVo> getProfit(){

        List<Tuple> tuples = dailyRankingQueryDslmp.getRanking();
        List<ProfitListResponseVo> profitListResponseVo = tuples.stream()
                .map(this::maptoDto).toList();
        return profitListResponseVo;

    }

    @Override
    public MyProfitResponseVo getMyProfit(String uuid){

        Optional<DailyRanking> dailyRanking = dailyRankingRepository.findByUuid(uuid);
        if(dailyRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.INCORRECT_UUID);

        }
        return new MyProfitResponseVo(dailyRanking.get().getNickname(),
                dailyRanking.get().getTodayranking(),dailyRanking.get().getChangeRanking()
                ,dailyRanking.get().getProfit());
    }

}
