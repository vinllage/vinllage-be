package xyz.vinllage.crawler.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCrawlSetting is a Querydsl query type for CrawlSetting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrawlSetting extends EntityPathBase<CrawlSetting> {

    private static final long serialVersionUID = 1247968199L;

    public static final QCrawlSetting crawlSetting = new QCrawlSetting("crawlSetting");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath cron = createString("cron");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath sourceUrl = createString("sourceUrl");

    public QCrawlSetting(String variable) {
        super(CrawlSetting.class, forVariable(variable));
    }

    public QCrawlSetting(Path<? extends CrawlSetting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCrawlSetting(PathMetadata metadata) {
        super(CrawlSetting.class, metadata);
    }

}

