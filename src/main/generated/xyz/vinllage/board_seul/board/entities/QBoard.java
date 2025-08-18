package xyz.vinllage.board_seul.board.entities;

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

    private static final long serialVersionUID = 794315192L;

    public static final QBoard board = new QBoard("board");

    public final xyz.vinllage.board_seul.entities.QAuthorityEntity _super = new xyz.vinllage.board_seul.entities.QAuthorityEntity(this);

    public final BooleanPath active = createBoolean("active");

    public final BooleanPath attachFile = createBoolean("attachFile");

    public final StringPath bid = createString("bid");

    public final StringPath category = createString("category");

    public final BooleanPath comment = createBoolean("comment");

    //inherited
    public final EnumPath<xyz.vinllage.member.constants.Authority> commentAuthority = _super.commentAuthority;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final BooleanPath editor = createBoolean("editor");

    public final BooleanPath imageUpload = createBoolean("imageUpload");

    //inherited
    public final EnumPath<xyz.vinllage.member.constants.Authority> listAuthority = _super.listAuthority;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> pageCount = createNumber("pageCount", Integer.class);

    public final NumberPath<Integer> rowsForPage = createNumber("rowsForPage", Integer.class);

    public final StringPath skin = createString("skin");

    //inherited
    public final EnumPath<xyz.vinllage.member.constants.Authority> viewAuthority = _super.viewAuthority;

    //inherited
    public final EnumPath<xyz.vinllage.member.constants.Authority> writeAuthority = _super.writeAuthority;

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

