package TMT.Ranking.weeklyranking.presentation;


import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.weeklyranking.application.WeeklyRankingServiceImp;
import TMT.Ranking.weeklyranking.vo.WeeklyMyRankingResponseVo;
import TMT.Ranking.weeklyranking.vo.WeeklyRankingResponseVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeeklyRankingController {

    private final WeeklyRankingServiceImp weeklyRankingServiceImp;
    private final DecodingToken decodingToken;

    @GetMapping("/weekly/revenue")
    public BaseResponse<List<WeeklyRankingResponseVo>> getWeeklyRanking(){

        List<WeeklyRankingResponseVo> weeklyRankingResponseVo =
                weeklyRankingServiceImp.getWeeklyRanking();
        return new BaseResponse<>(weeklyRankingResponseVo);

    }

    @GetMapping("/weekly/my-revenue")
    public BaseResponse<WeeklyMyRankingResponseVo> getLastWeekRanking(@RequestHeader
            ("Authorization") String jwt){

        String uuid = decodingToken.getUuid(jwt);
        WeeklyMyRankingResponseVo weeklyMyRankingResponseVo =
                weeklyRankingServiceImp.getMyWeeklyRanking(uuid);

        return new BaseResponse<>(weeklyMyRankingResponseVo);

    }

}
