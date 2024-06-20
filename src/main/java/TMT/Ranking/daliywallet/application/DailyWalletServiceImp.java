package TMT.Ranking.daliywallet.application;
import TMT.Ranking.daliywallet.domain.DailyWallet;
import TMT.Ranking.daliywallet.dto.DailyWalletInfoResponseDto;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletIQueryDslImp;
import TMT.Ranking.daliywallet.infrastructure.DailyWalletRepository;
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
    private final DailyWalletRepository dailyWalletInfoRepository;
    private final DailyWalletIQueryDslImp dailyWalletInfoQueryDslImp;


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

                dailyWalletInfoQueryDslImp.updateTodayWon(uuid, data.getWon(), data.getNickname());

            }else {
                DailyWallet dailyWalletinfo = DailyWallet.builder()
                        .uuid(uuid)
                        .todayWon(data.getWon())
                        .nickname(data.getNickname())
                        .build();
                dailyWalletInfoRepository.save(dailyWalletinfo);
            }
        }
    }
    @Override
    @Scheduled(cron = "0 10 17 ? * MON-FRI")
    public void updateYesterdayWon(){
        dailyWalletInfoQueryDslImp.updateYesterdayWon();
    }

    @Override
    @Scheduled(cron = "0 20 16 ? * MON") //매주 월요일
    public void updateMondayWon(){
        dailyWalletInfoQueryDslImp.updateMondayWon();
        log.info("lastMondayWon update");
    }

    @Override
    @Scheduled(cron = "0 20 16 ? * FRI") //매주 금요일
    public void updateFridayWon(){

        dailyWalletInfoQueryDslImp.updateMondayWon();
        log.info("fridayWon update");
    }

    @Override
    @Scheduled(cron = "0 40 16 1 * *") //매월 1일
    public void updateLastMonthWon(){
        dailyWalletInfoQueryDslImp.updateLastMonthWon();
        log.info("lastMonthWon update");
    }

    @Override
    @Scheduled(cron = "0 40 16 L * *")  //매월 말일
    public void updateLastMonthEndWon(){

        dailyWalletInfoQueryDslImp.updateLastMonthEndWon();
        log.info("lastMonthEndWon update");
    }

}


