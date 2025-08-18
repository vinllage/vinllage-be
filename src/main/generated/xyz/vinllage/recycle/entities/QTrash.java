package xyz.vinllage.recycle.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrash is a Querydsl query type for Trash
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrash extends EntityPathBase<Trash> {

    private static final long serialVersionUID = 878151675L;

    public static final QTrash trash = new QTrash("trash");

    public final NumberPath<Long> rid = createNumber("rid", Long.class);

    public final EnumPath<xyz.vinllage.recycle.constants.TrashState> trashState = createEnum("trashState", xyz.vinllage.recycle.constants.TrashState.class);

    public final NumberPath<Long> x = createNumber("x", Long.class);

    public final NumberPath<Long> y = createNumber("y", Long.class);

    public QTrash(String variable) {
        super(Trash.class, forVariable(variable));
    }

    public QTrash(Path<? extends Trash> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrash(PathMetadata metadata) {
        super(Trash.class, metadata);
    }

}

