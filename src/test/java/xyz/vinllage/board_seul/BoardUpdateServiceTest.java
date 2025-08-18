// 1. 실제 DB 사용 통합 테스트
package xyz.vinllage.board_seul;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import xyz.vinllage.board_seul.board.entities.Board;
import xyz.vinllage.board_seul.board.repositories.BoardRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  // JPA 관련 컴포넌트만 로드 (Redis 제외)
@ActiveProfiles("test")
@DisplayName("Board Repository 테스트")
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시판 저장 및 조회 테스트")
    void saveAndFind() {
        // Given
        Board board = new Board();
        board.setBid("test_board");
        board.setName("테스트 게시판");
        board.setCreatedAt(LocalDateTime.now());

        // When: 저장
        Board savedBoard = boardRepository.save(board);

        // Then: 저장 확인
        assertNotNull(savedBoard);
        assertEquals("test_board", savedBoard.getBid());

        // When: 조회
        Optional<Board> foundBoard = boardRepository.findById("test_board");

        // Then: 조회 확인
        assertTrue(foundBoard.isPresent());
        assertEquals("테스트 게시판", foundBoard.get().getName());

        System.out.println("저장된 게시판: " + foundBoard.get());
    }
}