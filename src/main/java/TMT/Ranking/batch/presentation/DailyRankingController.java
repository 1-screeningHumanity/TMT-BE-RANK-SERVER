package TMT.Ranking.batch.presentation;

import TMT.Ranking.batch.application.DailyRankingServiceImp;
import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DailyRankingController {

    private final DailyRankingServiceImp dailyRankingServiceImp;
    private final DecodingToken decodingToken;


    @GetMapping("/revenue") //랭킹 정보 return
    public BaseResponse<List<ProfitListResponseVo>> getProfit(){

        List<ProfitListResponseVo> profitListResponseVo = dailyRankingServiceImp.getProfit();
        return new BaseResponse<>(profitListResponseVo);

    }

    @GetMapping("/my-revenue") //내 랭킹 등수 조회
    public BaseResponse<MyProfitResponseVo> getMYProfit(@RequestHeader ("Authorization")String jwt){

        String uuid = decodingToken.getUuid(jwt);
        MyProfitResponseVo myProfitResponseVo = dailyRankingServiceImp.getMyProfit(uuid);
        return new BaseResponse<>(myProfitResponseVo);

    }


}
