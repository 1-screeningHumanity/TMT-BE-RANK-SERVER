package TMT.Ranking.batch.infrastructure;

import TMT.Ranking.batch.dto.DailyRankingDto;
import TMT.Ranking.kafka.dto.NicknameChangeDto;
import com.querydsl.core.Tuple;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


public interface DailyRankingQueryDsl {

        //수익률 정산
    void updateDailyRanking(DailyRankingDto dailyRankingDto);

    void updateNickname(NicknameChangeDto nicknameChangeDto);

    void updateDailyRank();

    void updateYesterdayRanking();

    void updateChangeRanking();

    List<Tuple> getRanking();

}
