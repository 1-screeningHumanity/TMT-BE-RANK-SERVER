package TMT.Ranking.daliywallet.infrastructure;


import com.querydsl.jpa.impl.JPAQueryFactory;
import static TMT.Ranking.daliywallet.domain.QDailyWallet.dailyWallet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DailyWalletIQueryDslImp implements DailyWalletQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    @Transactional
    public void updateTodayWon(String uuid, Long won){

        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.todayWon, won)
                .where(dailyWallet.uuid.eq(uuid))
                .execute();

    }

    @Override
    @Transactional
    public void updateMondayWon(){

        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.lastMondayWon, dailyWallet.todayWon)
                .execute();

        log.info("lastMondayWon update");
    }

    @Override
    @Transactional
    public void updateFridayWon(){

        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.fridayWon, dailyWallet.todayWon)
                .execute();

        log.info("fridayWon update");
    }

    @Override
    @Transactional
    public void updateLastMonthWon(){
        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.lastMonthWon, dailyWallet.todayWon)
                .execute();

        log.info("lastMonthWon update");
    }


    @Override
    @Transactional
    public void updateLastMonthEndWon(){
        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.lastMonthEndWon, dailyWallet.todayWon)
                .execute();

        log.info("lastMonthEndWon update");
    }

    @Override
    @Transactional //todayWon->yesterdayWon
    public void updateYesterdayWon(){
        jpaQueryFactory.update(dailyWallet)
                .set(dailyWallet.yesterdayWon, dailyWallet.todayWon)
                .execute();
    }
}
