package TMT.Ranking.monthlyranking.presentation;


import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import TMT.Ranking.monthlyranking.application.MonthlyRankingServiceImp;
import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.monthlyranking.vo.MonthlyRankingResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MonthlyRankingController {

    private final MonthlyRankingServiceImp monthlyRankingServiceImp;
    private final DecodingToken decodingToken;


    @GetMapping("/monthly/revenue")
    public BaseResponse<Page<MonthlyRankingResponseVo>> getMonthlyRanking(Pageable pageable){

        Page<MonthlyRankingResponseVo> monthlyRankingResponseVo =
                monthlyRankingServiceImp.getMonthlyRanking(pageable);

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
