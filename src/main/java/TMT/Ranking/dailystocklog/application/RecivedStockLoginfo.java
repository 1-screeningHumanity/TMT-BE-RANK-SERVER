package TMT.Ranking.dailystocklog.application;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "trade-server", url = "http://localhost:8082")
public interface RecivedStockLoginfo {


}
