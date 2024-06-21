package TMT.Ranking.monthlyranking.domain;


import TMT.Ranking.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MonthlyRanking extends BaseEntity {


    @Id
    @GeneratedValue
    private Long monthlyRankingId;

    private String uuid;

    private String nickname;

    private Long ranking;

    private Long LastMonthRanking;

    private Long won;

    private double profit;

    @Builder
    public MonthlyRanking(String uuid, String nickname, Long won, double profit) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.won = won;
        this.profit = profit;
    }



}
