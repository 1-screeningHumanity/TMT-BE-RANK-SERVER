package TMT.Ranking.batch.infrastructure;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.kafka.dto.NicknameChangeDto;
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
    public void updateDailyRanking(String uuid, Long won,
            double profit,Long ranking, String nickname)
    {
        jpaQueryFactory.update(dailyRanking)
                .set(dailyRanking.won, won)
                .set(dailyRanking.profit, profit)
                .set(dailyRanking.ranking, ranking)
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



}
