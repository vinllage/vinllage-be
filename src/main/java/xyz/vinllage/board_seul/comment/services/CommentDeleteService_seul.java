package xyz.vinllage.board_seul.comment.services;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.comment.entities.Comment;
import xyz.vinllage.board_seul.comment.repositories.CommentRepository;
import xyz.vinllage.board_seul.repositories.BaseRepository;
import xyz.vinllage.board_seul.services.DeleteService;
import xyz.vinllage.board_seul.services.InfoService;

@Lazy
@Service
@Transactional
public class CommentDeleteService_seul extends DeleteService<Comment, Long> {
    private final CommentInfoService_seul commentInfoService;
    private final CommentRepository repository;


    public CommentDeleteService_seul(CommentInfoService_seul commentInfoService, CommentRepository repository) {
        this.commentInfoService = commentInfoService;
        this.repository = repository;
    }

    @Override
    protected InfoService<Comment, Long> getInfoService() {
        return commentInfoService;
    }

    @Override
    protected BaseRepository<Comment, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getNotFoundErrorMessage() {
        return "댓글을 찾을 수 없습니다.";
    }
}