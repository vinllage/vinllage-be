package xyz.vinllage.board.board.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.board.controllers.RequestBoard;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.board.repositories.BoardRepository;
import xyz.vinllage.board.repositories.BaseRepository;
import xyz.vinllage.board.services.UpdateService;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardUpdateService extends UpdateService<Board, String> {

    private final BoardRepository repository;


    @Override
    protected BaseRepository<Board, String> getRepository() {
        return repository;
    }

    @Override
    public Board beforeProcess(Board item) {
        // Board 저장 전 처리 로직
        // 예: 유효성 검사, 데이터 변환 등
        return item;
    }

    @Override
    public void afterProcess(Board item) {
        // Board 저장 후 처리 로직
        // 예: 캐시 갱신, 알림 발송 등
    }

}
