package TMT.Ranking.monthlyranking.infrastructure;

import static TMT.Ranking.monthlyranking.domain.QMonthlyRanking.monthlyRanking;

import TMT.Ranking.monthlyranking.domain.MonthlyRanking;
import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MonthlyRankingQueryDslImp implements MonthlyRankingQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;



    @Override
    public void monthlyRankingCreate(MonthlyRankingDto monthlyRankingDto){

        jpaQueryFactory
                .update(monthlyRanking)
                .set(monthlyRanking.won,monthlyRankingDto.getWon())
                .set(monthlyRanking.profit,monthlyRankingDto.getProfit())
                .set(monthlyRanking.nickname,monthlyRankingDto.getNickname())
                .where(monthlyRanking.uuid.eq(monthlyRankingDto.getUuid()))
                .execute();
    }

    @Override
    @Transactional // 월간순위 업데이트
    public void monthlyRankingUpdate(){

        List<MonthlyRanking> rankings = jpaQueryFactory
                .selectFrom(monthlyRanking)
                .orderBy(monthlyRanking.profit.desc())
                .fetch();

        long rank = 1;
        double previousProfit = Double.MIN_VALUE;
        long sameProfitCount = 0;

        for (int i = 0; i < rankings.size(); i++) {
            MonthlyRanking ranking = rankings.get(i);

            if (ranking.getProfit() == previousProfit) {
                sameProfitCount++;
            } else {
                rank += sameProfitCount;
                sameProfitCount = 1;
                previousProfit = ranking.getProfit();
            }

            jpaQueryFactory.update(monthlyRanking)
                    .set(monthlyRanking.ranking, rank)
                    .where(monthlyRanking.uuid.eq(ranking.getUuid()))
                    .execute();
        }

    }

    @Transactional
    @Override
    public void updateMonthlyRankingChange(){
        jpaQueryFactory
                .update(monthlyRanking)
                .set(monthlyRanking.changeRanking, monthlyRanking.lastMonthRanking
                        .subtract(monthlyRanking.ranking))
                .execute();
    }

    @Transactional
    @Override
    public void updateLastMonthRanking(){

        jpaQueryFactory
                .update(monthlyRanking)
                .set(monthlyRanking.lastMonthRanking, monthlyRanking.ranking)
                .execute();
    }

}
