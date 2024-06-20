package TMT.Ranking.daliywallet.application;

import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "member-server", url = "${FEIGNCLIENT.URL}")
public interface RecivedMessage {

    //FeinClient 설정
    @GetMapping("/send/dailywalletinfo")
    DailyWalletInfoResponseDto recivedMessage();


}
