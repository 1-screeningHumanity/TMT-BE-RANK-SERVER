package TMT.Ranking.batch.presentation;

import TMT.Ranking.batch.application.DailyRankingServiceImp;
import TMT.Ranking.batch.vo.MemberDailyRankingResponseVo;
import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.global.common.response.BaseResponse;
import TMT.Ranking.global.common.token.DecodingToken;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DailyRankingController {

    private final DailyRankingServiceImp dailyRankingServiceImp;
    private final DecodingToken decodingToken;


    @GetMapping("/revenue") //일일랭킹 정보 return
    public BaseResponse<Page<ProfitListResponseVo>> getProfit(Pageable pageable){

        Page<ProfitListResponseVo> profitListResponseVo = dailyRankingServiceImp.getProfit(pageable);
        return new BaseResponse<>(profitListResponseVo);

    }

    @GetMapping("/my-revenue") //내 일일랭킹 등수 조회
    public BaseResponse<MyProfitResponseVo> getMYProfit(@RequestHeader ("Authorization")String jwt){

        String uuid = decodingToken.getUuid(jwt);
        MyProfitResponseVo myProfitResponseVo = dailyRankingServiceImp.getMyProfit(uuid);
        return new BaseResponse<>(myProfitResponseVo);

    }

    @GetMapping("/members/{nickname}")
    public BaseResponse<MemberDailyRankingResponseVo> getMemberDailyRanking(
            @PathVariable String nickname){

        MemberDailyRankingResponseVo memberDailyRankingResponseVo =
                dailyRankingServiceImp.getMemberDailyRanking(nickname);
        return  new BaseResponse<>(memberDailyRankingResponseVo);

    }

}
