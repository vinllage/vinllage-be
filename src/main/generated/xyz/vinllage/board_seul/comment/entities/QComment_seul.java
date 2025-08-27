package xyz.vinllage.board_seul.comment.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment_seul is a Querydsl query type for Comment_seul
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment_seul extends EntityPathBase<Comment_seul> {

    private static final long serialVersionUID = 1740968880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment_seul comment_seul = new QComment_seul("comment_seul");

    public final xyz.vinllage.board_seul.entities.QBoardEntity_seul _super;

    public final NumberPath<Long> boardDataSeq = createNumber("boardDataSeq", Long.class);

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt;

    //inherited
    public final StringPath guestPw;

    //inherited
    public final StringPath ip;

    // inherited
    public final xyz.vinllage.member.entities.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt;

    //inherited
    public final StringPath poster;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    //inherited
    public final StringPath ua;

    public QComment_seul(String variable) {
        this(Comment_seul.class, forVariable(variable), INITS);
    }

    public QComment_seul(Path<? extends Comment_seul> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment_seul(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment_seul(PathMetadata metadata, PathInits inits) {
        this(Comment_seul.class, metadata, inits);
    }

    public QComment_seul(Class<? extends Comment_seul> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new xyz.vinllage.board_seul.entities.QBoardEntity_seul(type, metadata, inits);
        this.content = _super.content;
        this.createdAt = _super.createdAt;
        this.deletedAt = _super.deletedAt;
        this.guestPw = _super.guestPw;
        this.ip = _super.ip;
        this.member = _super.member;
        this.modifiedAt = _super.modifiedAt;
        this.poster = _super.poster;
        this.ua = _super.ua;
    }

}

