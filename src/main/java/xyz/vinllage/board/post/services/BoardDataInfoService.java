package xyz.vinllage.board.post.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.board.services.BoardInfoService;
import xyz.vinllage.board.post.entities.BoardData;
import xyz.vinllage.board.post.repositories.BoardDataRepository;
import xyz.vinllage.board.services.InfoService;
import xyz.vinllage.global.exceptions.*;

@Lazy
@Service
@Transactional
@RequiredArgsConstructor
public class BoardDataInfoService{
    private final BoardInfoService infoService;
    private final BoardDataRepository boardDataRepository;
}
