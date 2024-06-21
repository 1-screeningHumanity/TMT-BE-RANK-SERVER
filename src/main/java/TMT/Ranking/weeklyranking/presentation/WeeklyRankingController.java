package TMT.Ranking.weeklyranking.presentation;


import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.weeklyranking.application.WeeklyRankingServiceImp;
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

    @GetMapping("/weekly/revenue")
    public BaseResponse<List<WeeklyRankingResponseVo>> getWeeklyRanking(){

        List<WeeklyRankingResponseVo> weeklyRankingResponseVo =
                weeklyRankingServiceImp.getWeeklyRanking();
        return new BaseResponse<>(weeklyRankingResponseVo);

    }

    @GetMapping("/weekly/my-revenue")
    public BaseResponse<List<WeeklyRankingResponseVo>> getLastWeekRanking(@RequestHeader
            ("Authorization") String jwt){



        return null;
    }
}
