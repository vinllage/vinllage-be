package xyz.vinllage.board_seul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import xyz.vinllage.board_seul.board.controllers.RequestBoard;
import xyz.vinllage.board_seul.board.entities.Board_seul;
import xyz.vinllage.board_seul.board.repositories.BoardRepository_seul_seul;
import xyz.vinllage.board_seul.board.services.BoardInfoService_seul;
import xyz.vinllage.board_seul.controllers.BoardSearch_seul;
import xyz.vinllage.global.search.ListData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("BoardInfoService getList 테스트")
class BoardSeulInfoServiceTest {

    @Autowired
    private BoardRepository_seul_seul boardRepositorySeul;

    private BoardInfoService_seul boardInfoServiceSeul;
    private MockHttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        // MockHttpServletRequest 생성
        mockRequest = new MockHttpServletRequest();

        // BoardInfoService 수동 생성
        boardInfoServiceSeul = new BoardInfoService_seul(mockRequest, boardRepositorySeul, new ModelMapper());

        // 더미 데이터 생성
        createDummyBoards();
    }

    void createDummyBoards() {
        // 다양한 게시판 생성
        String[] boardIds = {"notice", "free", "qna", "gallery", "event"};
        String[] boardNames = {"공지사항", "자유게시판", "질문답변", "갤러리", "이벤트"};

        for (int i = 0; i < boardIds.length; i++) {
            Board_seul boardSeul = new Board_seul();
            boardSeul.setBid(boardIds[i]);
            boardSeul.setName(boardNames[i]);
            boardSeul.setSkin("default");
            boardSeul.setCreatedAt(LocalDateTime.now().minusDays(i)); // 생성일 다르게

            boardRepositorySeul.save(boardSeul);
        }

        System.out.println("총 " + boardIds.length + "개 게시판 생성 완료");
    }

    @Test
    @DisplayName("전체 게시판 목록 조회 테스트")
    void getAllBoards() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertNotNull(result);
        assertNotNull(result.getItems());
        assertEquals(5, result.getItems().size()); // 5개 게시판
        assertNotNull(result.getPagination());
        assertEquals(5, result.getPagination().getTotal());
        assertEquals(1, result.getPagination().getLastPage());

        System.out.println("조회된 게시판 수: " + result.getItems().size());
        System.out.println("전체 게시판 수: " + result.getPagination().getTotal());

        // 게시판 정보 출력
        result.getItems().forEach(board -> {
            System.out.println("게시판: " + board.getBid() + " - " + board.getName());
        });
    }

    @Test
    @DisplayName("게시판 BID로 검색 테스트")
    void searchByBid() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setSopt("BID");
        search.setSkey("notice");
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals("notice", result.getItems().get(0).getBid());
        assertEquals("공지사항", result.getItems().get(0).getName());

        System.out.println("BID 'notice' 검색 결과: " + result.getItems().size() + "개");
        System.out.println("검색된 게시판: " + result.getItems().get(0).getName());
    }

    @Test
    @DisplayName("게시판 NAME으로 검색 테스트")
    void searchByName() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setSopt("NAME");
        search.setSkey("자유");
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertTrue(result.getItems().get(0).getName().contains("자유"));

        System.out.println("NAME '자유' 검색 결과: " + result.getItems().size() + "개");
        System.out.println("검색된 게시판: " + result.getItems().get(0).getName());
    }

    @Test
    @DisplayName("통합 검색(ALL) 테스트")
    void searchAll() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setSopt("ALL");
        search.setSkey("게시판");
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertNotNull(result);
        assertTrue(result.getItems().size() > 0);

        System.out.println("통합 검색 '게시판' 결과: " + result.getItems().size() + "개");

        // 검색 결과에 '게시판'이 포함되는지 확인
        result.getItems().forEach(board -> {
            boolean hasKeyword = board.getBid().contains("게시판") || board.getName().contains("게시판");
            System.out.println("검색된 게시판: " + board.getName() + " (키워드 포함: " + hasKeyword + ")");
        });
    }

    @Test
    @DisplayName("빈 검색 결과 테스트")
    void testEmptyResult() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setSopt("BID");
        search.setSkey("nonexistent");
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getItems().size());
        assertEquals(0, result.getPagination().getTotal());

        System.out.println("존재하지 않는 검색어 결과: " + result.getItems().size() + "개");
    }

    @Test
    @DisplayName("정렬 확인 테스트 (최신순)")
    void testSorting() {
        // Given
        BoardSearch_seul search = new BoardSearch_seul();
        search.setPage(1);
        search.setLimit(10);

        // When
        ListData<Board_seul> result = boardInfoServiceSeul.getList(search);

        // Then
        assertTrue(result.getItems().size() > 1);

        // 첫 번째가 두 번째보다 최신인지 확인
        Board_seul first = result.getItems().get(0);
        Board_seul second = result.getItems().get(1);

        assertTrue(first.getCreatedAt().isAfter(second.getCreatedAt()) ||
                first.getCreatedAt().isEqual(second.getCreatedAt()));

        System.out.println("첫 번째 게시판 생성일: " + first.getCreatedAt());
        System.out.println("두 번째 게시판 생성일: " + second.getCreatedAt());
        System.out.println("정렬 확인: 최신순 정렬됨");
    }
    @Test
    @DisplayName("getForm 테스트")
    void testForm() {

        // When
        RequestBoard board = boardInfoServiceSeul.getForm("notice");

        System.out.println("피카츄");
        System.out.println(board);
    }
}

