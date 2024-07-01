package TMT.Ranking.monthlyranking.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlyRankingResponseVo {

    private double profit;
    private String nickname;
    private Long ranking;
    private Long changeRanking;

    private Long id;
}
