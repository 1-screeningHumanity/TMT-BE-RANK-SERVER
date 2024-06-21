package TMT.Ranking.monthlyranking.application;


import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingQueryDslImp;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonthlyRankingServiceImp implements MonthlyRankingService{

    private final MonthlyRankingQueryDslImp monthlyRankingQueryDslImp;


    @Override  //월간 수익률 랭킹 정산 
    @Scheduled(cron = "0 30 16 L * *") //매월말일
    public void updateMonthlyRanking(){
        monthlyRankingQueryDslImp.monthlyRankingUpdate();
    }

    @Override //월간 수익률 랭킹 순위 변동 업데이트
    @Scheduled(cron = "0 15 16 ? * FRI")
    public void updateMonthlyRankingChange(){
        monthlyRankingQueryDslImp.updateMonthlyRankingChange();
    }

    @Override //지난달 월간 수익률 랭킹 순위 업데이트
    public void updateLastMonthRanking(){
        monthlyRankingQueryDslImp.updateLastMonthRanking();
    }




}
