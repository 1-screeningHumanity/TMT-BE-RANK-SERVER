package TMT.Ranking.assetranking.presentation;

import TMT.Ranking.assetranking.dto.AssetRankingDto;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomSkipListner implements SkipListener<DailyWallet, AssetRankingDto> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.error("Error in reading: " + t.getMessage());
        // 예외 처리 로직 추가 가능 (예: 특정 저장소에 기록)
    }

    @Override
    public void onSkipInProcess(DailyWallet item, Throwable t) {
        log.error("Error in processing item: " + item + ", error: " + t.getMessage());
        // 스킵된 데이터에 대한 추가 처리 (예: 스킵된 아이템 기록)
    }

    @Override
    public void onSkipInWrite(AssetRankingDto item, Throwable t) {
        log.error("Error in writing item: " + item + ", error: " + t.getMessage());
        // 스킵된 데이터에 대한 추가 처리
    }
}