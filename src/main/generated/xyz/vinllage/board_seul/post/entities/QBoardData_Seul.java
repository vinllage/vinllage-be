package xyz.vinllage.board_seul.post.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardData_seul is a Querydsl query type for BoardData_seul
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardData_seul extends EntityPathBase<BoardData_seul> {

    private static final long serialVersionUID = 619150L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardData_seul boardData_seul = new QBoardData_seul("boardData_seul");

    public final xyz.vinllage.board_seul.entities.QBoardEntity_seul _super;

    public final xyz.vinllage.board_seul.board.entities.QBoard_seul board;

    public final StringPath category = createString("category");

    //inherited
    public final StringPath content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt;

    public final StringPath gid = createString("gid");

    //inherited
    public final StringPath guestPw;

    //inherited
    public final StringPath ip;

    // inherited
    public final xyz.vinllage.member.entities.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt;

    public final BooleanPath plainText = createBoolean("plainText");

    //inherited
    public final StringPath poster;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath subject = createString("subject");

    //inherited
    public final StringPath ua;

    public QBoardData_seul(String variable) {
        this(BoardData_seul.class, forVariable(variable), INITS);
    }

    public QBoardData_seul(Path<? extends BoardData_seul> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardData_seul(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardData_seul(PathMetadata metadata, PathInits inits) {
        this(BoardData_seul.class, metadata, inits);
    }

    public QBoardData_seul(Class<? extends BoardData_seul> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new xyz.vinllage.board_seul.entities.QBoardEntity_seul(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new xyz.vinllage.board_seul.board.entities.QBoard_seul(forProperty("board")) : null;
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

