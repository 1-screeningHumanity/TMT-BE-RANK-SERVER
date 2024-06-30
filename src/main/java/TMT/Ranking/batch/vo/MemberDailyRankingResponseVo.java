package TMT.Ranking.batch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberDailyRankingResponseVo {

    private String nickname;
    private Long todayRanking;
    private Long won;
    private double profit;

}
