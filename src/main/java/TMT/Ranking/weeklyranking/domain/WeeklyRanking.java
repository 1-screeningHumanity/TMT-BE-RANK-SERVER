package TMT.Ranking.weeklyranking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WeeklyRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weeklyRankingId; //pk

    private String uuid; //uuid

    private String nickname; //nickname

    private Long won; //예수금

    private double profit; //수익률

    private Long ranking; //순위

    private Long lastWeekRanking; //지난주 순위

    private Long changeRanking; //순위변동

    @Builder
    public WeeklyRanking(String uuid, String nickname, Long won,
            double profit) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.won = won;
        this.profit = profit;
    }
}
