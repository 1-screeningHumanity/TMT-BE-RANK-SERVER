package TMT.Ranking.weeklyranking.infrastructure;


import static TMT.Ranking.weeklyranking.domain.QWeeklyRanking.weeklyRanking;

import TMT.Ranking.weeklyranking.domain.WeeklyRanking;
import TMT.Ranking.weeklyranking.dto.WeeklyRankingDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class WeeklyRankingQueryDslImp implements WeeklyRankingQeuryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional //주간 수익률 업데이트
    public void updateWeeklyRanking(WeeklyRankingDto weeklyRankingDto) {

        jpaQueryFactory
                .update(weeklyRanking)
                .set(weeklyRanking.won, weeklyRankingDto.getWon())
                .set(weeklyRanking.profit, weeklyRankingDto.getProfit())
                .set(weeklyRanking.nickname, weeklyRankingDto.getNickname())
                .where(weeklyRanking.uuid.eq(weeklyRankingDto.getUuid()))
                .execute();

    }

    @Override
    @Transactional //주간랭킹 등수 정산
    public void createWeeklyRanking(){
        List<WeeklyRanking> rankings = jpaQueryFactory
                .selectFrom(weeklyRanking)
                .orderBy(weeklyRanking.profit.desc())
                .fetch();

        long rank = 1;
        double previousProfit = Double.MIN_VALUE;
        long sameProfitCount = 0;

        for (int i = 0; i < rankings.size(); i++) {
            WeeklyRanking ranking = rankings.get(i);

            if (ranking.getProfit() == previousProfit) {
                sameProfitCount++;
            } else {
                rank += sameProfitCount;
                sameProfitCount = 1;
                previousProfit = ranking.getProfit();
            }

            jpaQueryFactory.update(weeklyRanking)
                    .set(weeklyRanking.ranking, rank)
                    .where(weeklyRanking.uuid.eq(ranking.getUuid()))
                    .execute();
        }
    }

    @Override
    @Transactional //주간랭킹 순위 변동 업데이트
    public void updateChangeWeeklyRanking(){

        NumberExpression<Long> changeRankingExpression = Expressions.cases()//조건부설정
                .when(weeklyRanking.lastWeekRanking.eq(0L)) //yesterday 가 0일 경우
                .then(0L) //todayranking 반환
                .otherwise(weeklyRanking.lastWeekRanking.subtract(weeklyRanking.ranking));

        jpaQueryFactory
                .update(weeklyRanking)
                .set(weeklyRanking.changeRanking, changeRankingExpression)
                .execute();

    }

    @Override
    @Transactional //지난주랭킹 업데이트
    public void updateLastWeekRanking(){
        jpaQueryFactory
                .update(weeklyRanking)
                .set(weeklyRanking.lastWeekRanking, weeklyRanking.ranking)
                .execute();
    }

    @Override //주간랭킹 순위 조회
    public List<Tuple> getWeeklyRanking(Pageable pageable){

        return jpaQueryFactory
                .select(weeklyRanking.changeRanking,weeklyRanking.won,
                        weeklyRanking.profit, weeklyRanking.nickname, weeklyRanking.ranking)
                .from(weeklyRanking)
                .orderBy(weeklyRanking.profit.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public long getWeeklyRankingCount(){

        return jpaQueryFactory
                .selectFrom(weeklyRanking)
                .fetchCount();
    }

}
