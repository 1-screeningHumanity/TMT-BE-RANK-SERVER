package TMT.Ranking.monthlyranking.presentation;


import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import TMT.Ranking.monthlyranking.application.MonthlyRankingServiceImp;
import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.monthlyranking.vo.MonthlyRankingResponseVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonthlyRankingController {

    private final MonthlyRankingServiceImp monthlyRankingServiceImp;
    private final DecodingToken decodingToken;


    @GetMapping("/monthly/revenue")
    public BaseResponse<List<MonthlyRankingResponseVo>> getMonthlyRanking(){

        List<MonthlyRankingResponseVo> monthlyRankingResponseVo =
                monthlyRankingServiceImp.getMonthlyRanking();

        return new BaseResponse<>(monthlyRankingResponseVo);

    }

    @GetMapping("/monthly/my-revenue")
    public BaseResponse<MonthlyMyRankingResponseVo> getMonthlyRanking(@RequestHeader
            ("Authorization") String jwt){

        String uuid = decodingToken.getUuid(jwt);
        MonthlyMyRankingResponseVo monthlyMyRankingResponseVo =
                monthlyRankingServiceImp.getMyMonthlyRanking(uuid);

        return new BaseResponse<>(monthlyMyRankingResponseVo);

    }

}
