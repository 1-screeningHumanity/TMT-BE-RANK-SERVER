package TMT.Ranking.weeklyranking.application;


import static TMT.Ranking.weeklyranking.domain.QWeeklyRanking.weeklyRanking;

import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import TMT.Ranking.weeklyranking.domain.WeeklyRanking;
import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingQueryDslImp;
import TMT.Ranking.weeklyranking.infrastructure.WeeklyRankingRepository;
import TMT.Ranking.weeklyranking.vo.WeeklyMyRankingResponseVo;
import TMT.Ranking.weeklyranking.vo.WeeklyRankingResponseVo;
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
public class WeeklyRankingServiceImp implements WeeklyRankingService{

    private final WeeklyRankingQueryDslImp weeklyRankingQueryDslImp;
    private final WeeklyRankingRepository weeklyRankingRepository;


    @Override //주간랭킹 정산
    @Scheduled(cron = "0 0 16 ? * FRI")
    public void createWeeklyRanking(){
        weeklyRankingQueryDslImp.createWeeklyRanking();
    }

    @Override //주간랭킹 순위변동 정산
    @Scheduled(cron = "0 02 16 ? * FRI")
    public void updateChangeWeeklyRanking(){
        weeklyRankingQueryDslImp.updateChangeWeeklyRanking();
    }

    @Override //주간랭킹 지난주순위 업데이트
    @Scheduled(cron = "0 04 16 ? * FRI")
    public void updateLastWeekRanking(){
        weeklyRankingQueryDslImp.updateLastWeekRanking();
    }

    private WeeklyRankingResponseVo maptoDto (Tuple tuple) { //tuple to WeeklyRankingResponseVo

        double profit  = tuple.get(weeklyRanking.profit);
        String nickname = tuple.get(weeklyRanking.nickname);
        Long ranking = tuple.get(weeklyRanking.ranking);
        Long changeRanking = tuple.get(weeklyRanking.changeRanking);
        return new WeeklyRankingResponseVo(nickname, profit, ranking, changeRanking);

    }

    @Override //주간랭킹 조회
    public Page<WeeklyRankingResponseVo> getWeeklyRanking(Pageable pageable){

        List<Tuple> tuples = weeklyRankingQueryDslImp.getWeeklyRanking(pageable);
        long total = weeklyRankingQueryDslImp.getWeeklyRankingCount();
        List<WeeklyRankingResponseVo> weeklyRankingResponseVoList = tuples.stream()
                .map(this::maptoDto).collect(Collectors.toList());

        return new PageImpl<>(weeklyRankingResponseVoList, pageable, total);
    }

    @Override
    public WeeklyMyRankingResponseVo getMyWeeklyRanking(String uuid){

        Optional<WeeklyRanking> weeklyRanking = weeklyRankingRepository.findByUuid(uuid);
        if(weeklyRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.INCORRECT_UUID);

        }
        return new WeeklyMyRankingResponseVo(weeklyRanking.get().getNickname(),
                weeklyRanking.get().getRanking(), weeklyRanking.get().getChangeRanking()
                ,weeklyRanking.get().getProfit());
    }

}
