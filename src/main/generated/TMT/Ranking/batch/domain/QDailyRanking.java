package TMT.Ranking.batch.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyRanking is a Querydsl query type for DailyRanking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDailyRanking extends EntityPathBase<DailyRanking> {

    private static final long serialVersionUID = -1388088280L;

    public static final QDailyRanking dailyRanking = new QDailyRanking("dailyRanking");

    public final TMT.Ranking.global.entity.QBaseEntity _super = new TMT.Ranking.global.entity.QBaseEntity(this);

    public final NumberPath<Long> changeRanking = createNumber("changeRanking", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> dailyRankingId = createNumber("dailyRankingId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Double> profit = createNumber("profit", Double.class);

    public final NumberPath<Long> todayranking = createNumber("todayranking", Long.class);

    public final StringPath uuid = createString("uuid");

    public final NumberPath<Long> won = createNumber("won", Long.class);

    public final NumberPath<Long> yesterdayRanking = createNumber("yesterdayRanking", Long.class);

    public QDailyRanking(String variable) {
        super(DailyRanking.class, forVariable(variable));
    }

    public QDailyRanking(Path<? extends DailyRanking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyRanking(PathMetadata metadata) {
        super(DailyRanking.class, metadata);
    }

}

