package TMT.Ranking.stockinfo.application;

import TMT.Ranking.kafka.dto.StockinfoDto;

public interface StockInfoService {

    void stockInfoSave(StockinfoDto stockinfoDto);
}
