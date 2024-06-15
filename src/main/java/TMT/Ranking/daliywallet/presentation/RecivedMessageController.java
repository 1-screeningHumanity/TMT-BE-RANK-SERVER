package TMT.Ranking.daliywallet.presentation;


import TMT.Ranking.daliywallet.application.DailyWalletServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Service
public class RecivedMessageController {

    private final DailyWalletServiceImp dailyWalletServiceImp;
    
    @GetMapping("/receive-message") //테스트용 컨트롤러
    public void reciveMessage() {
        // DailyWalletServiceImp의 메서드를 호출하여 다른 서버에서 데이터를 받아오고 저장
        dailyWalletServiceImp.walletInfoRequest();
    }

}
