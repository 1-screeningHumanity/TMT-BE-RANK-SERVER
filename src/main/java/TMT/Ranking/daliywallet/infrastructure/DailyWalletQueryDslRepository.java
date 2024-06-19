package TMT.Ranking.daliywallet.infrastructure;

import TMT.Ranking.kafka.dto.NicknameChangeDto;
import org.springframework.transaction.annotation.Transactional;

public interface DailyWalletQueryDslRepository {

    void updateTodayWon(String uuid, Long won, String nickname);

    void updateMondayWon();

    void updateFridayWon();

    void updateLastMonthWon();

    void updateLastMonthEndWon();

    void updateYesterdayWon();

    void updateNickname(NicknameChangeDto nicknameChangeDto);
}
