package TMT.Ranking.daliywallet.application;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoQueryDslImp;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
import TMT.Ranking.global.common.exception.CustomException;
import TMT.Ranking.global.common.response.BaseResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class DailyWalletServiceImp implements DailyWalletService {

    private final RecivedMessage recivedMessage;
    private final DailyWalletInfoRepository dailyWalletInfoRepository;
    private final DailyWalletInfoQueryDslImp dailyWalletInfoQueryDslImp;


    @Override
    @Scheduled(cron = "0 10 16 ? * MON-FRI") //오후 4시 10분에 실행
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
                uuid = uuid.substring(1, uuid.length() - 1);

            if (dailyWalletInfoRepository.existsByUuid(uuid)){

                dailyWalletInfoQueryDslImp.updateTodayWon(uuid, data.getWon());

            }else {
                DailyWallet dailyWalletinfo = DailyWallet.builder()
                        .uuid(uuid)
                        .todayWon(data.getWon())
                        .build();
                dailyWalletInfoRepository.save(dailyWalletinfo);
            }
        }
    }

    @Override
    @Scheduled(cron = "0 10 16 ? * MON")
    public void updateMondayWon(){
        dailyWalletInfoQueryDslImp.updateMondayWon();
    }

    @Override
    @Scheduled(cron = "0 10 16 ? * FRI")
    public void updateFridayWon(){
        dailyWalletInfoQueryDslImp.updateMondayWon();
    }

    @Override
    @Scheduled(cron = "0 10 16 1 * *")
    public void updateLastMonthWon(){
        dailyWalletInfoQueryDslImp.updateLastMonthWon();
    }

    @Override
    @Scheduled(cron = "0 10 16 L * *")
    public void updateLastMonthEndWon(){
        dailyWalletInfoQueryDslImp.updateLastMonthEndWon();
    }

}


