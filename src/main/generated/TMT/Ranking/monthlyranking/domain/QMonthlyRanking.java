package TMT.Ranking.monthlyranking.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonthlyRanking is a Querydsl query type for MonthlyRanking
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMonthlyRanking extends EntityPathBase<MonthlyRanking> {

    private static final long serialVersionUID = -1750206919L;

    public static final QMonthlyRanking monthlyRanking = new QMonthlyRanking("monthlyRanking");

    public final TMT.Ranking.global.entity.QBaseEntity _super = new TMT.Ranking.global.entity.QBaseEntity(this);

    public final NumberPath<Long> changeRanking = createNumber("changeRanking", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> lastMonthRanking = createNumber("lastMonthRanking", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> monthlyRankingId = createNumber("monthlyRankingId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final NumberPath<Double> profit = createNumber("profit", Double.class);

    public final NumberPath<Long> ranking = createNumber("ranking", Long.class);

    public final StringPath uuid = createString("uuid");

    public final NumberPath<Long> won = createNumber("won", Long.class);

    public QMonthlyRanking(String variable) {
        super(MonthlyRanking.class, forVariable(variable));
    }

    public QMonthlyRanking(Path<? extends MonthlyRanking> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthlyRanking(PathMetadata metadata) {
        super(MonthlyRanking.class, metadata);
    }

}

