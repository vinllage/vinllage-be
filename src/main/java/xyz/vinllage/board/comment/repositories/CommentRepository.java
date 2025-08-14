package xyz.vinllage.board.comment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.comment.entities.Comment;
import xyz.vinllage.board.repositories.BaseRepository;

public interface CommentRepository extends BaseRepository<Comment, Long> {

}