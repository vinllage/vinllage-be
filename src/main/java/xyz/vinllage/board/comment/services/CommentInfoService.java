package xyz.vinllage.board.comment.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vinllage.board.board.entities.QBoard;
import xyz.vinllage.board.comment.entities.Comment;
import xyz.vinllage.board.comment.entities.QComment;
import xyz.vinllage.board.comment.repositories.CommentRepository;
import xyz.vinllage.board.controllers.BoardSearch;
import xyz.vinllage.board.repositories.BaseRepository;
import xyz.vinllage.board.services.InfoService;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.List;

@Lazy
@Service
@Transactional
public class CommentInfoService extends InfoService<Comment, Long>{

    private final CommentRepository repository;
    private final MemberUtil memberUtil;
    private final ModelMapper mapper;
    private final CommentPermissionService permission;

    public CommentInfoService(HttpServletRequest request, CommentRepository repository, MemberUtil memberUtil, ModelMapper mapper, CommentPermissionService permission) {
        super(request);
        this.repository = repository;
        this.memberUtil = memberUtil;
        this.mapper = mapper;
        this.permission = permission;
    }


    @Override
    protected BaseRepository<Comment, Long> getRepository() {
        return repository;
    }

    @Override
    protected BooleanBuilder search(BoardSearch search) {
        BooleanBuilder andBuilder = new BooleanBuilder();
        QComment comment = QComment.comment;

        //특정 seq의 게시글에 달린 댓글만
        andBuilder.and(comment.boardDataSeq.eq(search.getSeq()));
        //deletedAt 필터링
        andBuilder.and(comment.deleted.isFalse());

        return andBuilder;
    }

    //후처리
    @Override
    public List<Comment> after(List<Comment> items){
        for (Comment commentItem : items) {
            commentItem.setCanDelete( (commentItem.getMember()==null) || memberUtil.isAdmin() || (memberUtil.isLogin() &&
                    commentItem.getMember().getEmail().equals(memberUtil.getMember().getEmail())));
            commentItem.setNeedAuth(permission.needAuth(commentItem));
        }

        return items;
    }


}
