package xyz.vinllage.board_seul.post.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardData_Seul is a Querydsl query type for BoardData_Seul
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardData_Seul extends EntityPathBase<BoardData_Seul> {

    private static final long serialVersionUID = -334162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardData_Seul boardData_Seul = new QBoardData_Seul("boardData_Seul");

    public final xyz.vinllage.board_seul.entities.QBoardEntity_seul _super;

    public final xyz.vinllage.board_seul.board.entities.QBoard_seul boardSeul;

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

    public QBoardData_Seul(String variable) {
        this(BoardData_Seul.class, forVariable(variable), INITS);
    }

    public QBoardData_Seul(Path<? extends BoardData_Seul> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardData_Seul(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardData_Seul(PathMetadata metadata, PathInits inits) {
        this(BoardData_Seul.class, metadata, inits);
    }

    public QBoardData_Seul(Class<? extends BoardData_Seul> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new xyz.vinllage.board_seul.entities.QBoardEntity_seul(type, metadata, inits);
        this.boardSeul = inits.isInitialized("boardSeul") ? new xyz.vinllage.board_seul.board.entities.QBoard_seul(forProperty("boardSeul")) : null;
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

