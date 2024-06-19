package TMT.Ranking.batch.infrastructure;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class DailyRankingQueryDslmp implements DailyRankingQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    @Override
    public void updateDailyRanking(String uuid, Long won, double profit){
        jpaQueryFactory.update(dailyRanking)
                .set(dailyRanking.won, won)
                .set(dailyRanking.profit, profit)
                .where(dailyRanking.uuid.eq(uuid))
                .execute();
    }



}
