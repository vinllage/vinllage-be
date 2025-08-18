package xyz.vinllage.board_seul.post.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardData is a Querydsl query type for BoardData
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardData extends EntityPathBase<BoardData> {

    private static final long serialVersionUID = 580903674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardData boardData = new QBoardData("boardData");

    public final xyz.vinllage.board_seul.entities.QBoardEntity _super;

    public final xyz.vinllage.board_seul.board.entities.QBoard board;

    public final StringPath category = createString("category");

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

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

    public final BooleanPath notice = createBoolean("notice");

    public final BooleanPath plainText = createBoolean("plainText");

    //inherited
    public final StringPath poster;

    public final BooleanPath secret = createBoolean("secret");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath subject = createString("subject");

    //inherited
    public final StringPath ua;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QBoardData(String variable) {
        this(BoardData.class, forVariable(variable), INITS);
    }

    public QBoardData(Path<? extends BoardData> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardData(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardData(PathMetadata metadata, PathInits inits) {
        this(BoardData.class, metadata, inits);
    }

    public QBoardData(Class<? extends BoardData> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new xyz.vinllage.board_seul.entities.QBoardEntity(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new xyz.vinllage.board_seul.board.entities.QBoard(forProperty("board")) : null;
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

