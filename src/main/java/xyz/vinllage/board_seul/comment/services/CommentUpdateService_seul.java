package xyz.vinllage.board_seul.comment.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.comment.controllers.RequestComment;
import xyz.vinllage.board_seul.comment.entities.Comment;
import xyz.vinllage.board_seul.comment.repositories.CommentRepository;
import xyz.vinllage.board_seul.repositories.BaseRepository;
import xyz.vinllage.board_seul.services.UpdateService;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.Objects;

@Service
@Transactional
public class CommentUpdateService_seul extends UpdateService<Comment, Long, RequestComment> {
    private final CommentRepository repository;
    private final HttpServletRequest request;
    private final MemberUtil memberUtil;

    public CommentUpdateService_seul(CommentRepository repository, HttpServletRequest request, MemberUtil memberUtil) {
        this.repository = repository;
        this.request = request;
        this.memberUtil = memberUtil;
    }

    @Override
    protected BaseRepository<Comment, Long> getRepository() {
        return repository;
    }

    @Override
    public Comment beforeProcess(RequestComment item) {
        String mode = Objects.requireNonNullElse(item.getMode(), "register");

        Comment comment = new Comment();
        comment.setBoardDataSeq(item.getBoardDataSeq());
        comment.setCommenter(item.getCommenter());
        comment.setContent(item.getContent());
        comment.setIp(request.getRemoteAddr());
        comment.setUa(request.getHeader("User-Agent"));

        if (memberUtil.isLogin()) {
            comment.setMember(memberUtil.getMember());
            comment.setCommenter(memberUtil.getMember().getName());
        } else {
            comment.setGuestPw(item.getGuestPw());
            comment.setCommenter(item.getCommenter());
        }

        return comment;
    }

    @Override
    public void afterProcess(Comment item) {

    }
}
