package TMT.Ranking.weeklyranking.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeeklyRankingResponseVo {

    private String nickname;
    private Long won;
    private Long profit;
    private Long ranking;
    private Long changeRanking;

}
