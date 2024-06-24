package TMT.Ranking.weeklyranking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeeklyMyRankingResponseVo {

    private String nickname;
    private Long ranking;
    private Long rankingChange;
    private double profit;

    public void getMyRanking(String nickname, Long ranking, Long rankingChange, double profit) {
        this.nickname = nickname;
        this.ranking = ranking;
        this.rankingChange = rankingChange;
        this.profit = profit;
    }

}
