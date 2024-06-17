package TMT.Ranking.daliywallet.application;

import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "member-server", url = "http://localhost:8082")
public interface RecivedMessage {
//로드벨런싱 잡기

    //FeinClient 설정
    @GetMapping("/send/dailywalletinfo")
    DailyWalletInfoResponseDto recivedMessage();


}
