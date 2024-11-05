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
        //
    }

    @Override
    public void onSkipInProcess(DailyWallet item, Throwable t) {
        log.error("Error in processing item: " + item + ", error: " + t.getMessage());

    }

    @Override
    public void onSkipInWrite(AssetRankingDto item, Throwable t) {
        log.error("Error in writing item: " + item + ", error: " + t.getMessage());

    }
}