package TMT.Ranking.monthlyranking.application;


import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingQueryDslImp;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyRankingServiceImp implements MonthlyRankingService{

    private final MonthlyRankingQueryDslImp monthlyRankingQueryDslImp;


    @Override
    //    @Scheduled(cron = "0 30 16 L * *") 매월말일
    @Scheduled(cron = "0 06 16 ? * FRI") //월간 수익률 집계
    public void updateMonthlyRanking(){
        monthlyRankingQueryDslImp.monthlyRankingUpdate();
    }




}
