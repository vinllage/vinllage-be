package xyz.vinllage.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.board.repositories.BoardRepository;
import xyz.vinllage.board.controllers.BoardSearch;
import xyz.vinllage.board.post.entities.BoardData;
import xyz.vinllage.board.post.repositories.BoardDataRepository;
import xyz.vinllage.board.post.services.BoardDataInfoService;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("BoardDataInfoService 테스트")
class BoardDataInfoServiceTest {

    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    private BoardDataInfoService boardDataInfoService;
    private ModelMapper mapper;
    private MockHttpServletRequest mockRequest;

    @BeforeEach
    void setUp() {
        mockRequest = new MockHttpServletRequest();
        mapper = new ModelMapper();

        // BoardDataInfoService 수동 생성
        boardDataInfoService = new BoardDataInfoService(mockRequest, boardDataRepository, mapper);

        // 테스트 데이터 생성
        createTestData();
    }

    void createTestData() {
        // 1. 테스트 회원 생성 (mobile 추가)
        Member member1 = new Member();
        member1.setEmail("test1@test.com");
        member1.setName("김철수");
        member1.setPassword("password123");
        member1.setMobile("010-1234-5678");  // 추가!
        member1.setCreatedAt(LocalDateTime.now());
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setEmail("test2@test.com");
        member2.setName("이영희");
        member2.setPassword("password123");
        member2.setMobile("010-9876-5432");  // 추가!
        member2.setCreatedAt(LocalDateTime.now());
        memberRepository.save(member2);

        // 2. 테스트 게시판 생성 (다른 필수 필드들도 추가)
        Board board1 = new Board();
        board1.setBid("notice");
        board1.setName("공지사항");
        board1.setSkin("default");
        board1.setActive(true);
        board1.setCategory("general");           // 필수 필드
        board1.setListAuthority(Authority.ALL);  // 필수 필드
        board1.setViewAuthority(Authority.ALL);  // 필수 필드
        board1.setWriteAuthority(Authority.MEMBER); // 필수 필드
        board1.setCommentAuthority(Authority.MEMBER); // 필수 필드
        board1.setRowsForPage(20);
        board1.setPageCount(10);
        board1.setCreatedAt(LocalDateTime.now());
        boardRepository.save(board1);

        Board board2 = new Board();
        board2.setBid("free");
        board2.setName("자유게시판");
        board2.setSkin("default");
        board2.setActive(true);
        board2.setCategory("general");
        board2.setListAuthority(Authority.ALL);
        board2.setViewAuthority(Authority.ALL);
        board2.setWriteAuthority(Authority.MEMBER);
        board2.setCommentAuthority(Authority.MEMBER);
        board2.setRowsForPage(20);
        board2.setPageCount(10);
        board2.setCreatedAt(LocalDateTime.now());
        boardRepository.save(board2);

        // 3. 게시글 생성 (seq 제거)
        String[] subjects = {
                "공지사항입니다", "자유게시판 글입니다", "질문이 있어요",
                "도움 요청드립니다", "감사합니다", "추천해주세요",
                "후기 공유합니다", "정보 공유", "문의사항", "이벤트 안내"
        };

        String[] contents = {
                "중요한 공지사항 내용입니다", "자유롭게 작성한 글입니다",
                "궁금한 점이 있어서 질문드립니다", "도움이 필요합니다",
                "많은 도움 받아 감사합니다", "좋은 제품 추천 부탁드려요",
                "사용 후기를 공유드립니다", "유용한 정보입니다",
                "문의할 내용이 있습니다", "재미있는 이벤트 소식"
        };

        Random random = new Random();
        Board[] boards = {board1, board2};
        Member[] members = {member1, member2};

        // 50개 게시글 생성
        for (int i = 1; i <= 50; i++) {
            BoardData boardData = new BoardData();
            // boardData.setSeq((long) i);  // 제거! 자동 생성
            boardData.setSubject(subjects[random.nextInt(subjects.length)] + " #" + i);
            boardData.setContent(contents[random.nextInt(contents.length)] + " (게시글 번호: " + i + ")");
            boardData.setPoster("작성자" + (i % 5 + 1));
            boardData.setBoard(boards[random.nextInt(boards.length)]);
            boardData.setMember(members[random.nextInt(members.length)]);

            // BoardData의 다른 필수 필드들도 설정
            boardData.setGid("gid_" + System.currentTimeMillis() + "_" + i);  // 고유값
            boardData.setViewCount(0);

            // 최근 30일 내 랜덤 날짜
            LocalDateTime randomTime = LocalDateTime.now()
                    .minusDays(random.nextInt(30))
                    .minusHours(random.nextInt(24));
            boardData.setCreatedAt(randomTime);

            boardDataRepository.save(boardData);
        }

        System.out.println("테스트 데이터 생성 완료: 회원 2명, 게시판 2개, 게시글 50개");
    }

    @Test
    @DisplayName("전체 게시글 목록 조회 테스트")
    void getAllBoardData() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        assertNotNull(result.getItems());
        assertTrue(result.getItems().size() <= 20);
        assertEquals(50, result.getPagination().getTotal());

        System.out.println("조회된 게시글 수: " + result.getItems().size());
        System.out.println("전체 게시글 수: " + result.getPagination().getTotal());
        System.out.println("전체 페이지 수: " + result.getPagination().getLastPage());

        // 첫 번째 게시글 정보 출력
        if (!result.getItems().isEmpty()) {
            BoardData first = result.getItems().get(0);
            System.out.println("첫 번째 게시글: " + first.getSubject());
            System.out.println("작성자: " + first.getPoster());
            System.out.println("게시판: " + first.getBoard().getName());
        }
    }

    @Test
    @DisplayName("제목으로 검색 테스트")
    void searchBySubject() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setSopt("SUBJECT");
        search.setSkey("공지");
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        assertTrue(result.getItems().size() > 0);

        System.out.println("'공지' 제목 검색 결과: " + result.getItems().size() + "개");

        // 검색된 게시글들이 모두 '공지'를 포함하는지 확인
        for (BoardData item : result.getItems()) {
            assertTrue(item.getSubject().contains("공지"));
            System.out.println("검색된 게시글: " + item.getSubject());
        }
    }

    @Test
    @DisplayName("내용으로 검색 테스트")
    void searchByContent() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setSopt("CONTENT");
        search.setSkey("도움");
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("'도움' 내용 검색 결과: " + result.getItems().size() + "개");

        for (BoardData item : result.getItems()) {
            assertTrue(item.getContent().contains("도움"));
            System.out.println("검색된 게시글: " + item.getSubject() + " - " + item.getContent());
        }
    }

    @Test
    @DisplayName("게시판 ID로 필터링 테스트")
    void searchByBoardId() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setBid(Arrays.asList("notice"));
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("'notice' 게시판 필터링 결과: " + result.getItems().size() + "개");

        for (BoardData item : result.getItems()) {
            assertEquals("notice", item.getBoard().getBid());
            System.out.println("게시글: " + item.getSubject() + " (게시판: " + item.getBoard().getName() + ")");
        }
    }

    @Test
    @DisplayName("회원 이메일로 검색 테스트")
    void searchByMemberEmail() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setEmail(Arrays.asList("test1@test.com"));
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("'test1@test.com' 회원 검색 결과: " + result.getItems().size() + "개");

        for (BoardData item : result.getItems()) {
            assertEquals("test1@test.com", item.getMember().getEmail());
            System.out.println("게시글: " + item.getSubject() + " (작성자: " + item.getMember().getName() + ")");
        }
    }

    @Test
    @DisplayName("날짜 범위 검색 테스트")
    void searchByDateRange() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setSDate(LocalDate.now().minusDays(7));  // 7일 전부터
        search.setEDate(LocalDate.now());               // 오늘까지
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("최근 7일 내 게시글: " + result.getItems().size() + "개");

        LocalDateTime weekAgo = LocalDate.now().minusDays(7).atStartOfDay();
        LocalDateTime today = LocalDate.now().atTime(23, 59, 59);

        for (BoardData item : result.getItems()) {
            assertTrue(item.getCreatedAt().isAfter(weekAgo) || item.getCreatedAt().isEqual(weekAgo));
            assertTrue(item.getCreatedAt().isBefore(today) || item.getCreatedAt().isEqual(today));
            System.out.println("게시글: " + item.getSubject() + " (작성일: " + item.getCreatedAt() + ")");
        }
    }

    @Test
    @DisplayName("통합 검색 테스트")
    void searchAll() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setSopt("ALL");
        search.setSkey("정보");
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("'정보' 통합 검색 결과: " + result.getItems().size() + "개");

        for (BoardData item : result.getItems()) {
            boolean hasKeyword = item.getSubject().contains("정보") ||
                    item.getContent().contains("정보") ||
                    item.getPoster().contains("정보") ||
                    item.getMember().getName().contains("정보") ||
                    item.getMember().getEmail().contains("정보");
            assertTrue(hasKeyword);
            System.out.println("검색된 게시글: " + item.getSubject());
        }
    }

    @Test
    @DisplayName("페이징 테스트")
    void testPaging() {
        // Given
        BoardSearch search1 = new BoardSearch();
        search1.setPage(1);
        search1.setLimit(10);

        BoardSearch search2 = new BoardSearch();
        search2.setPage(2);
        search2.setLimit(10);

        // When
        ListData<BoardData> page1 = boardDataInfoService.getList(search1);
        ListData<BoardData> page2 = boardDataInfoService.getList(search2);

        // Then
        assertEquals(10, page1.getItems().size());
        assertEquals(10, page2.getItems().size());
        assertEquals(50, page1.getPagination().getTotal());
        assertEquals(5, page1.getPagination().getLastPage());

        // 다른 페이지의 첫 번째 게시글이 다른지 확인
        assertNotEquals(page1.getItems().get(0).getSeq(), page2.getItems().get(0).getSeq());

        System.out.println("1페이지 첫 게시글: " + page1.getItems().get(0).getSubject());
        System.out.println("2페이지 첫 게시글: " + page2.getItems().get(0).getSubject());
    }

    @Test
    @DisplayName("단일 게시글 조회 테스트")
    void getSingleBoardData() {

        BoardSearch search = new BoardSearch();
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result1 = boardDataInfoService.getList(search);

        BoardData first=result1.getItems().getFirst();
        Long seq=first.getSeq();

        BoardData result= boardDataInfoService.get(seq);

        System.out.println("조회된 게시글:");
        System.out.println("  제목: " + result.getSubject());
        System.out.println("  내용: " + result.getContent());
        System.out.println("  작성자: " + result.getPoster());
        System.out.println("  게시판: " + result.getBoard().getName());
        System.out.println("  회원: " + result.getMember().getName());
    }

    @Test
    @DisplayName("복합 조건 검색 테스트")
    void complexSearch() {
        // Given
        BoardSearch search = new BoardSearch();
        search.setBid(Arrays.asList("free"));           // 자유게시판만
        search.setEmail(Arrays.asList("test1@test.com")); // 특정 회원만
        search.setSopt("SUBJECT");                       // 제목 검색
        search.setSkey("자유");                          // '자유' 키워드
        search.setPage(1);
        search.setLimit(20);

        // When
        ListData<BoardData> result = boardDataInfoService.getList(search);

        // Then
        assertNotNull(result);
        System.out.println("복합 조건 검색 결과: " + result.getItems().size() + "개");

        for (BoardData item : result.getItems()) {
            assertEquals("free", item.getBoard().getBid());
            assertEquals("test1@test.com", item.getMember().getEmail());
            assertTrue(item.getSubject().contains("자유"));
            System.out.println("검색된 게시글: " + item.getSubject() +
                    " (게시판: " + item.getBoard().getName() +
                    ", 작성자: " + item.getMember().getName() + ")");
        }
    }
}