package TMT.Ranking.assetranking.presentation;


import TMT.Ranking.assetranking.application.AssetRankingServiceImp;
import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.assetranking.vo.MyAssetRankingResponseVo;
import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AssetRankingController {

    private final AssetRankingServiceImp assetRankingServiceImp;
    private final DecodingToken decodingToken;


    @GetMapping("/asset") //랭킹 정보 return
    public BaseResponse<Page<AssetRankingResponseVo>> getProfit(Pageable pageable){

       Page<AssetRankingResponseVo> assetRankingResponseVo =
                assetRankingServiceImp.getAssetRanking(pageable);
        return new BaseResponse<>(assetRankingResponseVo);

    }

    @GetMapping("/my-asset") //내 랭킹 등수 조회
    public BaseResponse<MyAssetRankingResponseVo> getMYProfit(@RequestHeader("Authorization")String jwt){

        String uuid = decodingToken.getUuid(jwt);
        MyAssetRankingResponseVo myAssetrankingResponseVo =
                assetRankingServiceImp.getMyAssetRanking(uuid);
        return new BaseResponse<>(myAssetrankingResponseVo);

    }


}
