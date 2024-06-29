package TMT.Ranking.assetranking.application;


import static TMT.Ranking.assetranking.domain.QAssetRanking.assetRanking;

import TMT.Ranking.assetranking.domain.AssetRanking;
import TMT.Ranking.assetranking.infrastructure.AssetRankingQueryDslImp;
import TMT.Ranking.assetranking.infrastructure.AssetRankingRepository;
import TMT.Ranking.assetranking.vo.AssetRankingResponseVo;
import TMT.Ranking.assetranking.vo.MyAssetRankingResponseVo;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import com.querydsl.core.Tuple;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetRankingServiceImp implements AssetRankingService {

    private final AssetRankingQueryDslImp assetRankingQueryDslImp;
    private final AssetRankingRepository assetRankingRepository;

    @Scheduled(cron = "0 16 16 ? * MON-FRI")
    @Override
    public void updateAssetRanking(){
        log.info("start AssetRanking");
        assetRankingQueryDslImp.updateRanking();
    }

    @Override
    @Scheduled(cron = "0 18 16 ? * MON-FRI")
    public void updateRankingChange(){
        log.info("start updateRankingChange");
        assetRankingQueryDslImp.updateRankingChange();
    }

    @Override
    @Scheduled(cron = "0 20 16 ? * MON-FRI")
    public void updateYesterdayRanking(){
        log.info("start updateYesterdayRanking");
        assetRankingQueryDslImp.updateYesterdayRanking();
    }

    @Override // 자산순위 반환
    public Page<AssetRankingResponseVo> getAssetRanking(Pageable pageable) {
        List<Tuple> tuples = assetRankingQueryDslImp.getAssetRanking(pageable);
        long total = assetRankingQueryDslImp.getAssetRankingCount(); // 전체 레코드 수
        List<AssetRankingResponseVo> assetRankingResponseVoList = tuples.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());

        return new PageImpl<>(assetRankingResponseVoList, pageable, total);
    }

    private AssetRankingResponseVo maptoDto(Tuple tuple) { // Tuple -> AssetRankingResponseVo
        Long won  = tuple.get(assetRanking.won);
        String nickname = tuple.get(assetRanking.nickname);
        Long todayRanking = tuple.get(assetRanking.ranking);
        Long changeRanking = tuple.get(assetRanking.changeRanking);
        return new AssetRankingResponseVo(nickname, todayRanking, won, changeRanking);
    }

    @Override //내 자산랭킹정보 return
    public MyAssetRankingResponseVo getMyAssetRanking(String uuid){

        Optional<AssetRanking> assetRanking = assetRankingRepository.findByUuid(uuid);
        if(assetRanking.isEmpty()) {
            throw new CustomException(BaseResponseCode.INCORRECT_UUID);
        }

        return new MyAssetRankingResponseVo(assetRanking.get().getNickname(),
                assetRanking.get().getWon(),
                assetRanking.get().getChangeRanking(),
                assetRanking.get().getRanking());
    }

}
