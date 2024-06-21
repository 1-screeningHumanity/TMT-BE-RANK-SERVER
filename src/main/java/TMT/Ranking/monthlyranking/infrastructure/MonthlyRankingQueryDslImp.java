package TMT.Ranking.monthlyranking.infrastructure;

import static TMT.Ranking.monthlyranking.domain.QMonthlyRanking.monthlyRanking;

import TMT.Ranking.monthlyranking.dto.MonthlyRankingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

}
