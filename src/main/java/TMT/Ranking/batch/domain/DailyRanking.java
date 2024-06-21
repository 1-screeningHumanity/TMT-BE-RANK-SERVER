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

    private String nickname;

    private Long todayranking;

    private Long yesterdayRanking;

    private Long changeRanking;

    @Builder
    public DailyRanking(String uuid, Long won, double profit, String nickname, Long todayranking,
            Long yesterdayRanking, Long changeRanking) {
        this.uuid = uuid;
        this.won = won;
        this.profit = profit;
        this.nickname = nickname;
    }


}
