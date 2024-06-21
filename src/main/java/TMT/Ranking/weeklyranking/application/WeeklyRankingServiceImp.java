package TMT.Ranking.weeklyranking.application;


import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingQueryDslImp;
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







}
