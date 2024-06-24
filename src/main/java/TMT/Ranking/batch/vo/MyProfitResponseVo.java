package TMT.Ranking.batch.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyProfitResponseVo {

    private String nickname;
    private Long todayRanking;
    private Long changeRanking;
    private double profit;


}
