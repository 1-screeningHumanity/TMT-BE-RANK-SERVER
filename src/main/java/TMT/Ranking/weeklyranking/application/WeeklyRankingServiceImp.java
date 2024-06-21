package TMT.Ranking.weeklyranking.application;


import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;
import static TMT.Ranking.weeklyranking.domain.QWeeklyRanking.weeklyRanking;

import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingQueryDslImp;
import TMT.Ranking.weeklyranking.vo.WeeklyRankingResponseVo;
import com.querydsl.core.Tuple;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeeklyRankingServiceImp implements WeeklyRankingService{

    private final WeeklyRankingQueryDslImp weeklyRankingQueryDslImp;


    @Override //주간랭킹 정산
    @Scheduled(cron = "0 20 17 ? * FRI")
    public void createWeeklyRanking(){
        weeklyRankingQueryDslImp.createWeeklyRanking();
    }

    @Override //주간랭킹 순위변동 정산
    @Scheduled(cron = "0 30 17 ? * FRI")
    public void updateChangeWeeklyRanking(){
        weeklyRankingQueryDslImp.updateChangeWeeklyRanking();
    }

    @Override //주간랭킹 지난주순위 업데이트
    @Scheduled(cron = "0 40 17 ? * FRI")
    public void updateLastWeekRanking(){
        weeklyRankingQueryDslImp.updateLastWeekRanking();
    }

    private WeeklyRankingResponseVo maptoDto (Tuple tuple) { //tuple to dto
        Long won = tuple.get(weeklyRanking.won);
        double profit  = tuple.get(weeklyRanking.profit);
        String nickname = tuple.get(weeklyRanking.nickname);
        Long ranking = tuple.get(weeklyRanking.ranking);
        Long changeRanking = tuple.get(weeklyRanking.changeRanking);
        return new WeeklyRankingResponseVo(nickname, won, profit, ranking, changeRanking);
    }

    @Override //주간랭킹 조회 리스트
    public List<WeeklyRankingResponseVo> getWeeklyRanking(){

        List<Tuple> tuples = weeklyRankingQueryDslImp.getWeeklyRanking();
        List<WeeklyRankingResponseVo> weeklyRankingResponseVo = tuples.stream()
                .map(this::maptoDto).toList();
        return weeklyRankingResponseVo;
    }

}
