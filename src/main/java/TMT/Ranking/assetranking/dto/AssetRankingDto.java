package TMT.Ranking.assetranking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class AssetRankingDto {

    private String nickname;
    private String uuid;
    private Long won;

    @Builder
    public AssetRankingDto(String nickname, String uuid, Long won) {
        this.nickname = nickname;
        this.uuid = uuid;
        this.won = won;
    }

}
