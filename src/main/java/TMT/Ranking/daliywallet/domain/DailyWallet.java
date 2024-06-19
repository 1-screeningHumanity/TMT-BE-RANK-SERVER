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
    private Long dailyWalletId; //wallet id
    private String uuid; //uuid
    private Long yesterdayWon; //어제자산
    private Long todayWon; //오늘 자산
    private Long lastMondayWon; //저번주 월요일 자산
    private Long fridayWon; //금요일 자산
    private Long lastMonthWon; //지난달 첫날 자산
    private Long lastMonthEndWon; //지난달 마지막날 자산


    @Builder
    public DailyWallet(Long dailyWalletId, String uuid, Long yesterdayWon, Long todayWon,
            Long lastMondayWon, Long fridayWon, Long lastMonthWon, Long lastMonthEndWon) {
        this.dailyWalletId = dailyWalletId;
        this.uuid = uuid;
        this.yesterdayWon = yesterdayWon;
        this.todayWon = todayWon;
        this.lastMondayWon = lastMondayWon;
        this.fridayWon = fridayWon;
        this.lastMonthWon = lastMonthWon;
        this.lastMonthEndWon = lastMonthEndWon;
    }
}
