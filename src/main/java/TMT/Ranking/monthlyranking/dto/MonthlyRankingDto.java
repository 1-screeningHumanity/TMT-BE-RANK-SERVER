package TMT.Ranking.monthlyranking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonthlyRankingDto {

    private String uuid;
    private Long won;
    private double profit;
    private String nickname;

    @Builder
    public MonthlyRankingDto(String uuid, Long won, double profit,String nickname) {
        this.uuid = uuid;
        this.won = won;
        this.profit = profit;
        this.nickname = nickname;

    }

}
