package TMT.Ranking.weeklyranking.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeeklyRanking is a Querydsl query type for WeeklyRanking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeeklyRanking extends EntityPathBase<WeeklyRanking> {

    private static final long serialVersionUID = 184403983L;

    public static final QWeeklyRanking weeklyRanking = new QWeeklyRanking("weeklyRanking");

    public final TMT.Ranking.global.entity.QBaseEntity _super = new TMT.Ranking.global.entity.QBaseEntity(this);

    public final NumberPath<Long> changeRanking = createNumber("changeRanking", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> lastWeekRanking = createNumber("lastWeekRanking", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Double> profit = createNumber("profit", Double.class);

    public final NumberPath<Long> ranking = createNumber("ranking", Long.class);

    public final StringPath uuid = createString("uuid");

    public final NumberPath<Long> weeklyRankingId = createNumber("weeklyRankingId", Long.class);

    public final NumberPath<Long> won = createNumber("won", Long.class);

    public QWeeklyRanking(String variable) {
        super(WeeklyRanking.class, forVariable(variable));
    }

    public QWeeklyRanking(Path<? extends WeeklyRanking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeeklyRanking(PathMetadata metadata) {
        super(WeeklyRanking.class, metadata);
    }

}

