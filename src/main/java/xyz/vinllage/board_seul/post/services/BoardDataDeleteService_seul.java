package xyz.vinllage.board_seul.post.services;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.comment.repositories.CommentRepository;
import xyz.vinllage.board_seul.comment.services.CommentInfoService_seul;
import xyz.vinllage.board_seul.post.entities.BoardData;
import xyz.vinllage.board_seul.post.repositories.BoardDataRepository;
import xyz.vinllage.board_seul.repositories.BaseRepository;
import xyz.vinllage.board_seul.services.DeleteService;
import xyz.vinllage.board_seul.services.InfoService;

@Lazy
@Service
@Transactional
public class BoardDataDeleteService_seul extends DeleteService<BoardData,Long> {
    private final BoardDataInfoService_seul infoService;
    private final BoardDataRepository repository;


    public BoardDataDeleteService_seul(BoardDataInfoService_seul infoService, BoardDataRepository repository) {
        this.infoService = infoService;
        this.repository = repository;
    }

    @Override
    protected InfoService<BoardData, Long> getInfoService() {
        return infoService;
    }

    @Override
    protected BaseRepository<BoardData, Long> getRepository() {
        return repository;
    }

    @Override
    protected String getNotFoundErrorMessage() {
        return "글을 찾을 수 없습니다.";
    }

}
