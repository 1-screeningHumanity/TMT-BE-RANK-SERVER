package TMT.Ranking.batch.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfitListResponseVo {

    private double profit;
    private String nickname;
    private Long todayRanking;
    private Long changeRanking;
    private Long id;
}
