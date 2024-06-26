package TMT.Ranking.batch.infrastructure;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.batch.dto.DailyRankingDto;
import TMT.Ranking.kafka.dto.NicknameChangeDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class DailyRankingQueryDslmp implements DailyRankingQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    @Override //수익률 정산
    public void updateDailyRanking(DailyRankingDto dailyRankingDto)
    {
        jpaQueryFactory.update(dailyRanking)
                .set(dailyRanking.won,dailyRankingDto.getTodayWon())
                .set(dailyRanking.profit, dailyRankingDto.getProfit())
                .set(dailyRanking.nickname, dailyRankingDto.getNickname())
                .where(dailyRanking.uuid.eq(dailyRankingDto.getUuid()))
                .execute();
    }

    @Transactional
    @Override //닉네임 변경
    public void updateNickname(NicknameChangeDto nicknameChangeDto){

        jpaQueryFactory.update(dailyRanking)
                .set(dailyRanking.nickname, nicknameChangeDto.getAfterNickName())
                .where(dailyRanking.nickname.eq(nicknameChangeDto.getBeforeNickName()))
                .execute();

    }

    @Override
    @Transactional // 랭킹정산
    public void updateDailyRank(){

        List<DailyRanking> rankings = jpaQueryFactory
                .selectFrom(dailyRanking)
                .orderBy(dailyRanking.profit.desc())
                .fetch();

        long rank = 1;
        double previousProfit = Double.MIN_VALUE;
        long sameProfitCount = 0;

        for (int i = 0; i < rankings.size(); i++) {
            DailyRanking ranking = rankings.get(i);

            if (ranking.getProfit() == previousProfit) {
                sameProfitCount++;
            } else {
                rank += sameProfitCount;
                sameProfitCount = 1;
                previousProfit = ranking.getProfit();
            }
            
            jpaQueryFactory.update(dailyRanking)
                    .set(dailyRanking.todayranking, rank)
                    .where(dailyRanking.uuid.eq(ranking.getUuid()))
                    .execute();
        }

    }

    @Override
    @Transactional //어제 등수 업데이트
    public void updateYesterdayRanking(){

        jpaQueryFactory
                .update(dailyRanking)
                .set(dailyRanking.yesterdayRanking, dailyRanking.todayranking)
                .execute();
    }

    @Override
    @Transactional //등수 변동 업데이트
    public void updateChangeRanking(){

        NumberExpression<Long> changeRankingExpression = Expressions.cases()//조건부설정
                .when(dailyRanking.yesterdayRanking.eq(0L)) //yesterday 가 0일 경우
                .then(0L) //todayranking 반환
                .otherwise(dailyRanking.yesterdayRanking.subtract(dailyRanking.todayranking));

        jpaQueryFactory
                .update(dailyRanking)
                .set(dailyRanking.changeRanking, changeRankingExpression)
                .execute();

    }


    @Override //RankingList
    public List<Tuple> getRanking(){

        return jpaQueryFactory
                .select(dailyRanking.profit, dailyRanking.nickname,
                        dailyRanking.todayranking, dailyRanking.changeRanking)
                .from(dailyRanking)
                .orderBy(dailyRanking.profit.desc())
                .fetch();

    }

}
