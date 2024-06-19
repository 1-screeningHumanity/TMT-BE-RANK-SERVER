package TMT.Ranking.daliywallet.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyWallet is a Querydsl query type for DailyWallet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDailyWallet extends EntityPathBase<DailyWallet> {

    private static final long serialVersionUID = 675201609L;

    public static final QDailyWallet dailyWallet = new QDailyWallet("dailyWallet");

    public final TMT.Ranking.global.entity.QBaseEntity _super = new TMT.Ranking.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> dailyWalletId = createNumber("dailyWalletId", Long.class);

    public final NumberPath<Long> fridayWon = createNumber("fridayWon", Long.class);

    public final NumberPath<Long> lastMondayWon = createNumber("lastMondayWon", Long.class);

    public final NumberPath<Long> lastMonthEndWon = createNumber("lastMonthEndWon", Long.class);

    public final NumberPath<Long> lastMonthWon = createNumber("lastMonthWon", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> todayWon = createNumber("todayWon", Long.class);

    public final StringPath uuid = createString("uuid");

    public final NumberPath<Long> yesterdayWon = createNumber("yesterdayWon", Long.class);

    public QDailyWallet(String variable) {
        super(DailyWallet.class, forVariable(variable));
    }

    public QDailyWallet(Path<? extends DailyWallet> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyWallet(PathMetadata metadata) {
        super(DailyWallet.class, metadata);
    }

}

