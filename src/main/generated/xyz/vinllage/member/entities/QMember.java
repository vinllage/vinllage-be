package xyz.vinllage.member.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -711534904L;

    public static final QMember member = new QMember("member1");

    public final xyz.vinllage.global.entities.QBaseEntity _super = new xyz.vinllage.global.entities.QBaseEntity(this);

    public final EnumPath<xyz.vinllage.member.constants.Authority> authority = createEnum("authority", xyz.vinllage.member.constants.Authority.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> credentialChangedAt = createDateTime("credentialChangedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> expired = createDateTime("expired", java.time.LocalDateTime.class);

    public final StringPath gid = createString("gid");

    public final BooleanPath locked = createBoolean("locked");

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final BooleanPath termsAgree = createBoolean("termsAgree");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

