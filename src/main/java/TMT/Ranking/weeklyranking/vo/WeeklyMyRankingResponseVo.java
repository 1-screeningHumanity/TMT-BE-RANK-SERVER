package TMT.Ranking.weeklyranking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeeklyMyRankingResponseVo {

    private String nickname;
    private Long ranking;
    private Long rankingChaneg;

    public void getMyRanking(String nickname, Long ranking, Long rankingChaneg) {
        this.nickname = nickname;
        this.ranking = ranking;
        this.rankingChaneg = rankingChaneg;
    }

}
