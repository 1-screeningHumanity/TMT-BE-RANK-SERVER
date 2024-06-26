package TMT.Ranking.monthlyranking.domain;


import TMT.Ranking.global.entity.BaseEntity;
import jakarta.persistence.Column;
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
public class MonthlyRanking extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monthlyRankingId;

    private String uuid;

    private String nickname;

    private Long ranking;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long lastMonthRanking;

    private Long won;

    private double profit;

    private Long changeRanking; //순위변동


    @Builder
    public MonthlyRanking(String uuid, String nickname, Long won, double profit) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.won = won;
        this.profit = profit;
    }



}
