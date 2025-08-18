package xyz.vinllage.board_seul.board.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board_seul.board.controllers.RequestBoard;
import xyz.vinllage.board_seul.board.entities.Board;
import xyz.vinllage.board_seul.board.repositories.BoardRepository;
import xyz.vinllage.board_seul.repositories.BaseRepository;
import xyz.vinllage.board_seul.services.UpdateService;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardUpdateService_seul extends UpdateService<Board, String, RequestBoard> {

    private final BoardRepository repository;
    private final ModelMapper mapper;


    @Override
    protected BaseRepository<Board, String> getRepository() {
        return repository;
    }

    @Override
    public Board beforeProcess(RequestBoard form) {

        Board item = mapper.map(form, Board.class);
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
