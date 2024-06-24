package TMT.Ranking.weeklyranking.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class WeeklyRankingResponseVo {

    private String nickname;
    private double profit;
    private Long ranking;
    private Long changeRanking;

}
