package TMT.Ranking.monthlyranking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlyMyRankingResponseVo {


    private String nickname;
    private Long ranking;
    private Long changeRanking;
    private double profit;


}
