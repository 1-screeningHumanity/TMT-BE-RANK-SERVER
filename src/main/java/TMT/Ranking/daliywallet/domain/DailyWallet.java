package TMT.Ranking.daliywallet.domain;

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
public class DailyWallet extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyWalletId;
    private String uuid;
    private Long won;

    @Builder
    public DailyWallet(String uuid, Long won) {

        this.uuid = uuid;
        this.won = won;

    }

}
