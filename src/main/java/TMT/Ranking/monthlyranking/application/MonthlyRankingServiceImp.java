package TMT.Ranking.monthlyranking.application;


import static TMT.Ranking.monthlyranking.domain.QMonthlyRanking.monthlyRanking;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import TMT.Ranking.monthlyranking.domain.MonthlyRanking;
import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingQueryDslImp;
import TMT.Ranking.monthlyranking.infrastructure.MonthlyRankingRepository;
import TMT.Ranking.monthlyranking.vo.MonthlyMyRankingResponseVo;
import TMT.Ranking.monthlyranking.vo.MonthlyRankingResponseVo;
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
public class MonthlyRankingServiceImp implements MonthlyRankingService{

    private final MonthlyRankingQueryDslImp monthlyRankingQueryDslImp;
    private final MonthlyRankingRepository monthlyRankingRepository;


    @Override  //월간 수익률 랭킹 정산 
    @Scheduled(cron = "0 08 16 L * ?") //매월말일
    public void updateMonthlyRanking(){
        monthlyRankingQueryDslImp.monthlyRankingUpdate();
    }

    @Override //월간 수익률 랭킹 순위 변동 업데이트
    @Scheduled(cron = "0 10 16 L * ?")
    public void updateMonthlyRankingChange(){
        monthlyRankingQueryDslImp.updateMonthlyRankingChange();
    }


    @Override
    @Scheduled(cron = "0 12 16 L * ?")//지난달 월간 수익률 랭킹 순위 업데이트
    public void updateLastMonthRanking(){
        monthlyRankingQueryDslImp.updateLastMonthRanking();
    }


    private MonthlyRankingResponseVo maptoDto (Tuple tuple) { //tuple to dto

        double profit  = tuple.get(monthlyRanking.profit);
        String nickname = tuple.get(monthlyRanking.nickname);
        Long ranking = tuple.get(monthlyRanking.ranking);
        Long changeRanking = tuple.get(monthlyRanking.changeRanking);
        return new MonthlyRankingResponseVo(profit, nickname,ranking,changeRanking);

    }

    @Override //월간수익률 랭킹 순위 return
    public Page<MonthlyRankingResponseVo> getMonthlyRanking(Pageable pageable){

        List<Tuple> tuples = monthlyRankingQueryDslImp.getMonthlyRanking(pageable);
        long total = monthlyRankingQueryDslImp.getMonthlyRankingCount();
        List<MonthlyRankingResponseVo> monthlyRankingResponseVoList = tuples.stream()
                .map(this::maptoDto).collect(Collectors.toList());
        return new PageImpl<>(monthlyRankingResponseVoList, pageable, total);

    }

    @Override
    public MonthlyMyRankingResponseVo getMyMonthlyRanking(String uuid){

        Optional<MonthlyRanking> monthlyRanking = monthlyRankingRepository.findByUuid(uuid);
        if(monthlyRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.INCORRECT_UUID);

        }
        return new MonthlyMyRankingResponseVo(monthlyRanking.get().getNickname(),
                monthlyRanking.get().getRanking(),monthlyRanking.get().getChangeRanking()
                ,monthlyRanking.get().getProfit());

    }
}
