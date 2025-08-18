package xyz.vinllage.board_seul.board.repositories;

import xyz.vinllage.board_seul.board.entities.Board;
import xyz.vinllage.board_seul.repositories.BaseRepository;

public interface BoardRepository extends BaseRepository<Board, String> {
    boolean existsByBid(String bid);
}
