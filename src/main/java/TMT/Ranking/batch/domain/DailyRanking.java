package TMT.Ranking.batch.domain;


import TMT.Ranking.global.entity.BaseEntity;
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
public class DailyRanking extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyRankingId;

    private String uuid;

    private Long won;

    private double profit;
    @Builder
    public DailyRanking(Long dailyRankingId, String uuid, Long won, double profit) {
        this.dailyRankingId = dailyRankingId;
        this.uuid = uuid;
        this.won = won;
        this.profit = profit;
    }
}
