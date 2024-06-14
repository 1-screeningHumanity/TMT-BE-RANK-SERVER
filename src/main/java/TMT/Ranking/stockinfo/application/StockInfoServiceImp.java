package TMT.Ranking.stockinfo.application;


import TMT.Ranking.kafka.dto.StockinfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockInfoServiceImp implements StockInfoService{
    private RedisTemplate<String, Object> redisTemplate;

    //StockInfo Redis에 저장
    @Override
    public void stockInfoSave(StockinfoDto stockinfoDto){

        String key = stockinfoDto.getStockCode();
        redisTemplate.opsForValue().set(key, stockinfoDto);

    }
}
