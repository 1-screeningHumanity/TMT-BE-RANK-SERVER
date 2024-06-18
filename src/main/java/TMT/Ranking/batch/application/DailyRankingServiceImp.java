//package TMT.Ranking.batch.application;
//
//import TMT.Ranking.batch.domain.DailyRanking;
//import TMT.Ranking.batch.infrastructure.DailyRankingRepository;
//import TMT.Ranking.daliywallet.domain.DailyWallet;
//import TMT.Ranking.daliywallet.infrastructure.DailyWalletInfoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class DailyRankingServiceImp implements DailyRankingService {
//
//    private final DailyWalletInfoRepository dailyWalletInfoRepository;
//    private final DailyRankingRepository dailyRankingRepository;
//
//    @Override
//    public DailyRanking createprofit(DailyWallet dailyWallet) {
//
////        DailyWallet yesterdayWallet = dailyWalletInfoRepository.findByUuidAndCreatedAt(
////                dailyWallet.getUuid(), yesterday);
////        if (yesterdayWallet == null) {
////            throw new CustomException(BaseResponseCode.EMPTY_YESTERDAYWALLET);
////        }
////        double profit = ((dailyWallet.getWon() - yesterdayWallet.getWon())
////                / (double) yesterdayWallet.getWon()) * 100;
////
////        DailyRanking dailyRanking = DailyRanking.builder()
////                .uuid(dailyWallet.getUuid())
////                .won(dailyWallet.getWon())
////                .profit(profit)
////                .build();
////
////            dailyRankingRepository.save(dailyRanking);
////
////         return dailyRanking;
//        return null;
//    }
//}
