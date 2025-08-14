package xyz.vinllage.event.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCrawledArticle is a Querydsl query type for CrawledArticle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrawledArticle extends EntityPathBase<CrawledArticle> {

    private static final long serialVersionUID = 254707572L;

    public static final QCrawledArticle crawledArticle = new QCrawledArticle("crawledArticle");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath link = createString("link");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> publishedAt = createDate("publishedAt", java.time.LocalDate.class);

    public final StringPath siteName = createString("siteName");

    public final StringPath title = createString("title");

    public QCrawledArticle(String variable) {
        super(CrawledArticle.class, forVariable(variable));
    }

    public QCrawledArticle(Path<? extends CrawledArticle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCrawledArticle(PathMetadata metadata) {
        super(CrawledArticle.class, metadata);
    }

}

