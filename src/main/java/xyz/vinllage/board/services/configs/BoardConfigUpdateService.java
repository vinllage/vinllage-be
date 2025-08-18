package xyz.vinllage.board.services.configs;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.controllers.RequestBoardConfig;
import xyz.vinllage.board.entities.Board;
import xyz.vinllage.board.repositories.BoardRepository;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardConfigUpdateService {
    private final ModelMapper mapper;
    private final BoardRepository boardRepository;

    public void process(RequestBoardConfig form) {

        Board item = mapper.map(form, Board.class);
        boardRepository.saveAndFlush(item);
    }
}