package TMT.Ranking.assetranking.infrastructure;

import static TMT.Ranking.assetranking.domain.QAssetRanking.assetRanking;

import TMT.Ranking.assetranking.domain.AssetRanking;
import TMT.Ranking.assetranking.dto.AssetRankingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class AssetRankingQueryDslImp implements AssetRankingQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;
    @Override //집계된 지갑 정보 update
    @Transactional
    public void updateAssetRanking(AssetRankingDto assetRankingDto){

        jpaQueryFactory
                .update(assetRanking)
                .set(assetRanking.nickname,assetRankingDto.getNickname())
                .set(assetRanking.won,assetRankingDto.getWon())
                .where(assetRanking.uuid.eq(assetRankingDto.getUuid()))
                .execute();

    }

    @Override //자산랭킹 순위부여
    @Transactional
    public void updateRanking(){

        List<AssetRanking> rankings = jpaQueryFactory
                .selectFrom(assetRanking)
                .orderBy(assetRanking.won.desc())
                .fetch();

        long rank = 1;
        long previousProfit = 0;
        long sameProfitCount = 0;

        for (int i = 0; i < rankings.size(); i++) {
            AssetRanking ranking = rankings.get(i);

            if (ranking.getWon() == previousProfit) {
                sameProfitCount++;
            } else {
                rank += sameProfitCount;
                sameProfitCount = 1;
                previousProfit = ranking.getWon();
            }

            jpaQueryFactory.update(assetRanking)
                    .set(assetRanking.ranking, rank)
                    .where(assetRanking.uuid.eq(ranking.getUuid()))
                    .execute();
        }

    }

    @Override
    @Transactional //자산 랭킹 변동 순위 정산
    public void updateRankingChange(){

        jpaQueryFactory
                .update(assetRanking)
                .set(assetRanking.changeRanking,
                        assetRanking.yesterdayRanking.subtract(assetRanking.ranking))
                .execute();

    }

    @Override
    @Transactional //어제 자산랭킹 순위 정산
    public void updateYesterdayRanking(){

        jpaQueryFactory
                .update(assetRanking)
                .set(assetRanking.yesterdayRanking, assetRanking.ranking)
                .execute();
    }
}
