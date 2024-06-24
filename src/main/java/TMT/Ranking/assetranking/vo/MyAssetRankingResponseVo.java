package TMT.Ranking.assetranking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyAssetRankingResponseVo {

    private String nickname;
    private Long won;
    private Long changeRanking;
    private Long ranking;

}
