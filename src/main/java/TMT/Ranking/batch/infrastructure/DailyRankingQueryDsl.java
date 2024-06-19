package TMT.Ranking.batch.infrastructure;

import org.springframework.transaction.annotation.Transactional;

public interface DailyRankingQueryDsl {

    @Transactional
    void updateDailyRanking(String uuid, Long won, double profit);
}
