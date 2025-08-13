package xyz.vinllage.board.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.post.entities.BoardData;
import xyz.vinllage.board.repositories.BaseRepository;

public interface BoardDataRepository extends BaseRepository<Board, String> {

}
