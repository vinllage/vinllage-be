package xyz.vinllage.board_seul.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardEntity_seul is a Querydsl query type for BoardEntity_seul
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBoardEntity_seul extends EntityPathBase<BoardEntity_seul> {

    private static final long serialVersionUID = 1336014389L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardEntity_seul boardEntity_seul = new QBoardEntity_seul("boardEntity_seul");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath guestPw = createString("guestPw");

    public final StringPath ip = createString("ip");

    public final xyz.vinllage.member.entities.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath poster = createString("poster");

    public final StringPath ua = createString("ua");

    public QBoardEntity_seul(String variable) {
        this(BoardEntity_seul.class, forVariable(variable), INITS);
    }

    public QBoardEntity_seul(Path<? extends BoardEntity_seul> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardEntity_seul(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardEntity_seul(PathMetadata metadata, PathInits inits) {
        this(BoardEntity_seul.class, metadata, inits);
    }

    public QBoardEntity_seul(Class<? extends BoardEntity_seul> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new xyz.vinllage.member.entities.QMember(forProperty("member")) : null;
    }

}

