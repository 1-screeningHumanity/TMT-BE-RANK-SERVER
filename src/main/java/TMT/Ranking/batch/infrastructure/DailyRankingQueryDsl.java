package TMT.Ranking.batch.infrastructure;

import TMT.Ranking.kafka.dto.NicknameChangeDto;
import org.springframework.transaction.annotation.Transactional;

public interface DailyRankingQueryDsl {

    void updateDailyRanking(String uuid, Long won,
            double profit, Long ranking, String nickname);

    void updateNickname(NicknameChangeDto nicknameChangeDto);
}
