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

    public void getMyProfit(String nickname, Long ranking, Long changeRanking, double profit) {
        this.nickname = nickname;
        this.ranking = ranking;
        this.changeRanking = changeRanking;
        this.profit = profit;
    }

}
