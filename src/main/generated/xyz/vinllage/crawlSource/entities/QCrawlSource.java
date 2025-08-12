package xyz.vinllage.crawlSource.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCrawlSource is a Querydsl query type for CrawlSource
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrawlSource extends EntityPathBase<CrawlSource> {

    private static final long serialVersionUID = 1867871734L;

    public static final QCrawlSource crawlSource = new QCrawlSource("crawlSource");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath url = createString("url");

    public QCrawlSource(String variable) {
        super(CrawlSource.class, forVariable(variable));
    }

    public QCrawlSource(Path<? extends CrawlSource> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCrawlSource(PathMetadata metadata) {
        super(CrawlSource.class, metadata);
    }

}

