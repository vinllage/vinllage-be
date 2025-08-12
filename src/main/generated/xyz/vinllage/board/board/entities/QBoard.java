package xyz.vinllage.board.board.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 358596222L;

    public static final QBoard board = new QBoard("board");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final BooleanPath afterWritingRedirect = createBoolean("afterWritingRedirect");

    public final BooleanPath attachFile = createBoolean("attachFile");

    public final StringPath bid = createString("bid");

    public final StringPath category = createString("category");

    public final BooleanPath comment = createBoolean("comment");

    public final EnumPath<xyz.vinllage.member.constants.Authority> commentAuthority = createEnum("commentAuthority", xyz.vinllage.member.constants.Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final BooleanPath editor = createBoolean("editor");

    public final BooleanPath imageUpload = createBoolean("imageUpload");

    public final EnumPath<xyz.vinllage.member.constants.Authority> listAuthority = createEnum("listAuthority", xyz.vinllage.member.constants.Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> pageCount = createNumber("pageCount", Integer.class);

    public final NumberPath<Integer> rowsForPage = createNumber("rowsForPage", Integer.class);

    public final BooleanPath showViewList = createBoolean("showViewList");

    public final StringPath skin = createString("skin");

    public final EnumPath<xyz.vinllage.member.constants.Authority> viewAuthority = createEnum("viewAuthority", xyz.vinllage.member.constants.Authority.class);

    public final EnumPath<xyz.vinllage.member.constants.Authority> writeAuthority = createEnum("writeAuthority", xyz.vinllage.member.constants.Authority.class);

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

