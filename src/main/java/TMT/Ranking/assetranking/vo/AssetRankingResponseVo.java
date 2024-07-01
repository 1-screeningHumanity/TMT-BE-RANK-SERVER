package TMT.Ranking.assetranking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssetRankingResponseVo {

    private String nickname;
    private Long ranking;
    private Long won;
    private Long changeRanking;
    private Long id;

}
