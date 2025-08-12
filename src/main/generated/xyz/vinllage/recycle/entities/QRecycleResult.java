package xyz.vinllage.recycle.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecycleResult is a Querydsl query type for RecycleResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecycleResult extends EntityPathBase<RecycleResult> {

    private static final long serialVersionUID = 1671482963L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecycleResult recycleResult = new QRecycleResult("recycleResult");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final xyz.vinllage.member.entities.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> rid = createNumber("rid", Long.class);

    public final NumberPath<Double> topConfidence = createNumber("topConfidence", Double.class);

    public QRecycleResult(String variable) {
        this(RecycleResult.class, forVariable(variable), INITS);
    }

    public QRecycleResult(Path<? extends RecycleResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecycleResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecycleResult(PathMetadata metadata, PathInits inits) {
        this(RecycleResult.class, metadata, inits);
    }

    public QRecycleResult(Class<? extends RecycleResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new xyz.vinllage.member.entities.QMember(forProperty("member")) : null;
    }

}

