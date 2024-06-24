package TMT.Ranking.assetranking.infrastructure;

import static TMT.Ranking.assetranking.domain.QAssetRanking.assetRanking;

import TMT.Ranking.assetranking.dto.AssetRankingDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AssetRankingQueryDslImp implements AssetRankingQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    public void updateAssetRanking(AssetRankingDto assetRankingDto){

        jpaQueryFactory
                .update(assetRanking)
                .set(assetRanking.nickname,assetRankingDto.getNickname())
                .set(assetRanking.won,assetRankingDto.getWon())
                .where(assetRanking.uuid.eq(assetRankingDto.getUuid()))
                .execute();

    }


}
