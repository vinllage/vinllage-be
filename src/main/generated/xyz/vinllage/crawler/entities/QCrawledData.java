package xyz.vinllage.crawler.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCrawledData is a Querydsl query type for CrawledData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCrawledData extends EntityPathBase<CrawledData> {

    private static final long serialVersionUID = 1245946994L;

    public static final QCrawledData crawledData = new QCrawledData("crawledData");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Integer> hash = createNumber("hash", Integer.class);

    public final BooleanPath html = createBoolean("html");

    public final StringPath image = createString("image");

    public final StringPath link = createString("link");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath title = createString("title");

    public QCrawledData(String variable) {
        super(CrawledData.class, forVariable(variable));
    }

    public QCrawledData(Path<? extends CrawledData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCrawledData(PathMetadata metadata) {
        super(CrawledData.class, metadata);
    }

}

