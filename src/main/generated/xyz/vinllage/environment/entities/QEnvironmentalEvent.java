package xyz.vinllage.environment.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEnvironmentalEvent is a Querydsl query type for EnvironmentalEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEnvironmentalEvent extends EntityPathBase<EnvironmentalEvent> {

    private static final long serialVersionUID = -1031307815L;

    public static final QEnvironmentalEvent environmentalEvent = new QEnvironmentalEvent("environmentalEvent");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath originalUrl = createString("originalUrl");

    public final StringPath summary = createString("summary");

    public final StringPath title = createString("title");

    public QEnvironmentalEvent(String variable) {
        super(EnvironmentalEvent.class, forVariable(variable));
    }

    public QEnvironmentalEvent(Path<? extends EnvironmentalEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEnvironmentalEvent(PathMetadata metadata) {
        super(EnvironmentalEvent.class, metadata);
    }

}

