package xyz.vinllage.board.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.entities.BoardData;
import xyz.vinllage.board.entities.BoardView;
import xyz.vinllage.board.entities.QBoardView;
import xyz.vinllage.board.repositories.BoardDataRepository;
import xyz.vinllage.board.repositories.BoardViewRepository;
import xyz.vinllage.member.libs.MemberUtil;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardViewCountService {
    private final BoardDataRepository boardDataRepository;
    private final BoardViewRepository boardViewRepository;
    private final MemberUtil memberUtil;

    public void update(Long seq) {
        try {
            int hash = memberUtil.getUserHash();
            BoardView item = new BoardView();
            item.setHash(hash);
            item.setSeq(seq);
            boardViewRepository.saveAndFlush(item);
            QBoardView boardView = QBoardView.boardView;

            int viewCount = (int)boardViewRepository.count(boardView.seq.eq(seq));
            BoardData boardData = boardDataRepository.findById(seq).orElse(null);
            if (boardData != null) {
                boardData.setViewCount(viewCount);
                boardDataRepository.saveAndFlush(boardData);
            }

        } catch (Exception e) {}
    }
}