package TMT.Ranking.batch.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyProfitResponseVo {

    private String nickname;
    private Long todayRanking;

    public void getMyProfit(String nickname, Long todayRanking) {
        this.nickname = nickname;
        this.todayRanking = todayRanking;
    }

}
