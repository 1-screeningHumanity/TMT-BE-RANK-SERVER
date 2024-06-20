package TMT.Ranking.batch.infrastructure;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.kafka.dto.NicknameChangeDto;
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
    @Override
    public void updateDailyRanking(String uuid, Long won,
            double profit, String nickname)
    {
        jpaQueryFactory.update(dailyRanking)
                .set(dailyRanking.won, won)
                .set(dailyRanking.profit, profit)
                .set(dailyRanking.nickname, nickname)
                .where(dailyRanking.uuid.eq(uuid))
                .execute();
    }

    @Transactional
    @Override
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
    @Transactional
    public void updateYesterdayRanking(){

        jpaQueryFactory
                .update(dailyRanking)
                .set(dailyRanking.yesterdayRanking, dailyRanking.todayranking)
                .execute();
    }

    @Override
    @Transactional
    public void updateChangeRanking(){

        jpaQueryFactory
                .update(dailyRanking)
                .set(dailyRanking.changeRanking,
                        dailyRanking.yesterdayRanking.subtract(dailyRanking.todayranking))
                .execute();
    }

}
