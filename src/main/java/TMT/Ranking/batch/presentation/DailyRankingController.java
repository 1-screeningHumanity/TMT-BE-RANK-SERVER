package TMT.Ranking.batch.presentation;


import TMT.Ranking.batch.application.DailyRankingServiceImp;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.global.common.response.BaseResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DailyRankingController {

    private final DailyRankingServiceImp dailyRankingServiceImp;


    @GetMapping("/revenue") //랭킹 정보 return
    public BaseResponse<List<ProfitListResponseVo>> getProfit(){

        List<ProfitListResponseVo> profitListResponseVo = dailyRankingServiceImp.getProfit();

        return new BaseResponse<>(profitListResponseVo);

    }

}
