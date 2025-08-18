package xyz.vinllage.board_seul.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthorityEntity is a Querydsl query type for AuthorityEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QAuthorityEntity extends EntityPathBase<AuthorityEntity_seul> {

    private static final long serialVersionUID = -772227952L;

    public static final QAuthorityEntity authorityEntity = new QAuthorityEntity("authorityEntity");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final EnumPath<xyz.vinllage.member.constants.Authority> commentAuthority = createEnum("commentAuthority", xyz.vinllage.member.constants.Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final EnumPath<xyz.vinllage.member.constants.Authority> listAuthority = createEnum("listAuthority", xyz.vinllage.member.constants.Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<xyz.vinllage.member.constants.Authority> viewAuthority = createEnum("viewAuthority", xyz.vinllage.member.constants.Authority.class);

    public final EnumPath<xyz.vinllage.member.constants.Authority> writeAuthority = createEnum("writeAuthority", xyz.vinllage.member.constants.Authority.class);

    public QAuthorityEntity(String variable) {
        super(AuthorityEntity_seul.class, forVariable(variable));
    }

    public QAuthorityEntity(Path<? extends AuthorityEntity_seul> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorityEntity(PathMetadata metadata) {
        super(AuthorityEntity_seul.class, metadata);
    }

}

