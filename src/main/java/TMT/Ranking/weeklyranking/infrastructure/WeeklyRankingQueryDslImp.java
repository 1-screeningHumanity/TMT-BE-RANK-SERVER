package TMT.Ranking.weeklyranking.infrastructure;

import static TMT.Ranking.weeklyranking.domain.QWeeklyRanking.weeklyRanking;
import TMT.Ranking.weeklyranking.dto.WeeklyRankingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class WeeklyRankingQueryDslImp implements WeeklyRankingQeuryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    @Transactional
    public void updateWeekliRanking(WeeklyRankingDto weeklyRankingDto) {

        jpaQueryFactory
                .update(weeklyRanking)
                .set(weeklyRanking.won, weeklyRankingDto.getWon())
                .set(weeklyRanking.profit, weeklyRankingDto.getProfit())
                .set(weeklyRanking.nickname, weeklyRankingDto.getNickname())
                .where(weeklyRanking.uuid.eq(weeklyRankingDto.getUuid()))
                .execute();

    }

}
