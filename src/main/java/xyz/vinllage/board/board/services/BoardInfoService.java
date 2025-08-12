package xyz.vinllage.board.board.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.board.repositories.BoardRepository;
import xyz.vinllage.board.repositories.BaseRepository;
import xyz.vinllage.board.services.InfoService;

@Lazy
@Service
@Transactional
public class BoardInfoService extends InfoService<Board, String> {

    private final BoardRepository repository;

    public BoardInfoService(HttpServletRequest request, BoardRepository repository) {
        super(request);
        this.repository = repository;
    }

    @Override
    protected BaseRepository<Board, String> getRepository() {
        return repository;
    }

}
