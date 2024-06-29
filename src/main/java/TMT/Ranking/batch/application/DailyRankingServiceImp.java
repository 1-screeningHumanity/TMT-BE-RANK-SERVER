package TMT.Ranking.batch.application;

import static TMT.Ranking.batch.domain.QDailyRanking.dailyRanking;

import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.batch.domain.DailyRanking;
import TMT.Ranking.batch.infrastructure.DailyRankingQueryDslmp;
import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
import TMT.Ranking.batch.vo.MemberDailyRankingResponseVo;
import TMT.Ranking.batch.vo.MyProfitResponseVo;
import TMT.Ranking.batch.vo.ProfitListResponseVo;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import com.querydsl.core.Tuple;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyRankingServiceImp implements DailyRankingService {

    private final DailyRankingQueryDslmp dailyRankingQueryDslmp;
    private final DailyRankingRepository dailyRankingRepository;

    @Override
    @Scheduled(cron = "0 52 15 ? * MON-FRI")
    public void createRank(){
        dailyRankingQueryDslmp.updateDailyRank();
    }

    @Override
    @Scheduled(cron = "0 54 15 ? * MON-FRI")
    public void updateChangeRanking(){
        dailyRankingQueryDslmp.updateChangeRanking();
    }

    @Override
    @Scheduled(cron = "0 56 15 ? * MON-FRI")
    public void updateYesterdayRanking(){
        dailyRankingQueryDslmp.updateYesterdayRanking();
    }

    private ProfitListResponseVo maptoDto (Tuple tuple) { //tuple to ProfitListResponseVo

        double profit  = tuple.get(dailyRanking.profit);
        String nickname = tuple.get(dailyRanking.nickname);
        Long todayRanking = tuple.get(dailyRanking.todayranking);
        Long changeRanking = tuple.get(dailyRanking.changeRanking);
        return new ProfitListResponseVo(profit, nickname, todayRanking, changeRanking);

    }

    @Override  //일일 랭킹 return
    public Page<ProfitListResponseVo> getProfit(Pageable pageable){

        List<Tuple> tuples = dailyRankingQueryDslmp.getRanking(pageable);
        long total = dailyRankingQueryDslmp.getDailyRankingCount();
        List<ProfitListResponseVo> profitListResponseVoList = tuples.stream()
                .map(this::maptoDto).collect(Collectors.toList());
        return new PageImpl<>(profitListResponseVoList, pageable, total);

    }

    @Override //내 일일랭킹 return
    public MyProfitResponseVo getMyProfit(String uuid){

        Optional<DailyRanking> dailyRanking = dailyRankingRepository.findByUuid(uuid);
        if(dailyRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.INCORRECT_UUID);

        }
        return new MyProfitResponseVo(dailyRanking.get().getNickname(),
                dailyRanking.get().getTodayranking(),dailyRanking.get().getChangeRanking()
                ,dailyRanking.get().getProfit());
    }

    @Override
    public MemberDailyRankingResponseVo getMemberDailyRanking(String nickname){

        Optional<DailyRanking> dailyRanking = dailyRankingRepository.findByNickname(nickname);
        if(dailyRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.WRONG_NICKNAME);
        }
        return new MemberDailyRankingResponseVo(dailyRanking.get().getNickname(),
                dailyRanking.get().getTodayranking());
    }

}
