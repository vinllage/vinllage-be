package xyz.vinllage.event.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCrawlerConfig is a Querydsl query type for CrawlerConfig
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrawlerConfig extends EntityPathBase<CrawlerConfig> {

    private static final long serialVersionUID = -1505706350L;

    public static final QCrawlerConfig crawlerConfig = new QCrawlerConfig("crawlerConfig");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath script = createString("script");

    public final StringPath siteName = createString("siteName");

    public QCrawlerConfig(String variable) {
        super(CrawlerConfig.class, forVariable(variable));
    }

    public QCrawlerConfig(Path<? extends CrawlerConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCrawlerConfig(PathMetadata metadata) {
        super(CrawlerConfig.class, metadata);
    }

}

