package TMT.Ranking.weeklyranking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WeeklyRankingDto {

    private String uuid;
    private String nickname;
    private Long won;
    private double profit;

    @Builder
    public WeeklyRankingDto(String uuid, String nickname, Long won, double profit) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.won = won;
        this.profit = profit;

    }
}
