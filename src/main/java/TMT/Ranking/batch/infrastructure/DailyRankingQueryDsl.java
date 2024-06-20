package TMT.Ranking.batch.infrastructure;

import TMT.Ranking.kafka.dto.NicknameChangeDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface DailyRankingQueryDsl {
    void updateDailyRanking(String uuid, Long won,
            double profit, String nickname);
    void updateNickname(NicknameChangeDto nicknameChangeDto);

    void updateDailyRank();

    void updateYesterdayRanking();

    void updateChangeRanking();

    List<Tuple> getRanking();

}
