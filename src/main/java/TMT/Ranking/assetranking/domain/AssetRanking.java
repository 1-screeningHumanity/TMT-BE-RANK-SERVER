package TMT.Ranking.assetranking.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AssetRanking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetRankingId;

    private String uuid;

    private String nickname;

    private Long ranking;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long yesterdayRanking;

    private Long changeRanking;

    private Long won;

    @Builder
    public AssetRanking(String uuid, String nickname, Long ranking, Long yesterdayRanking,
            Long changeRanking, Long won) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.ranking = ranking;
        this.yesterdayRanking = yesterdayRanking;
        this.changeRanking = changeRanking;
        this.won = won;
    }

}
