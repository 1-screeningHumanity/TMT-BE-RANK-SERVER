package TMT.Ranking.daliywallet.application;

import static org.apache.commons.lang3.StringUtils.substring;

import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DailyWalletServiceImp implements DailyWalletService {

    private final RecivedMessage recivedMessage;
    private final DailyWalletInfoRepository dailyWalletInfoRepository;


    @Override
    @Scheduled(cron = "0 10 16 * * *") //오후 4시 10분에 실행
    public void walletInfoRequest() {

        // Feign 클라이언트를 통해, walletinfo 받아옴
        DailyWalletInfoResponseDto response = recivedMessage.recivedMessage();

        if (response == null) {
            throw  new CustomException(BaseResponseCode.WRONG_URL);
        }

        saveDailyWallet(response);
    }

    @Override //지갑데이터 저장
    public void saveDailyWallet(DailyWalletInfoResponseDto dailyWalletInfoResponseDto) {

        for (DailyWalletInfoResponseDto.DataDto data : dailyWalletInfoResponseDto.getData()) {

            String uuid = data.getUuid();
            if (uuid.startsWith("\"") && uuid.endsWith("\"")) {
                uuid = uuid.substring(1, uuid.length() - 1);
            }DailyWallet dailyWallet = new DailyWallet(uuid, data.getWon());
            dailyWalletInfoRepository.save(dailyWallet);

        }
    }
}


