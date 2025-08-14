package xyz.vinllage.board.post.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.vinllage.board.board.entities.Board;
import xyz.vinllage.board.board.repositories.BoardRepository;
import xyz.vinllage.board.board.services.BoardInfoService;
import xyz.vinllage.board.post.controllers.RequestBoardData;
import xyz.vinllage.board.post.entities.BoardData;
import xyz.vinllage.board.post.repositories.BoardDataRepository;
import xyz.vinllage.board.repositories.BaseRepository;
import xyz.vinllage.board.services.UpdateService;
import xyz.vinllage.member.libs.MemberUtil;

@Lazy
@Service
@Transactional
public class BoardDataUpdateService extends UpdateService<BoardData, Long, RequestBoardData> {

    private final BoardDataRepository repository;
    private final BoardInfoService boardInfoService;
    private final MemberUtil memberUtil;
    private final HttpServletRequest request;
    private final PasswordEncoder encoder;

    // 생성자
    public BoardDataUpdateService(BoardDataRepository repository,
                                  BoardRepository boardRepository,
                                  BoardInfoService boardInfoService,
                                  MemberUtil memberUtil,
                                  HttpServletRequest request,
                                  PasswordEncoder encoder) {
        this.repository = repository;
        this.boardInfoService = boardInfoService;
        this.memberUtil = memberUtil;
        this.request = request;
        this.encoder = encoder;
    }

    @Override
    protected BaseRepository<BoardData, Long> getRepository() {
        return repository;
    }

    // 전처리 - RequestBoardData를 받아서 BoardData를 반환
    @Override
    public BoardData beforeProcess(RequestBoardData form) {
        String bid = form.getBid();
        Long seq = form.getSeq();
        String gid = form.getGid();

        // 게시판 설정 보기
        Board board = boardInfoService.get(bid);

        BoardData item = null;
        if (seq != null && seq > 0L && (item = repository.findById(seq).orElse(null)) != null) {
            // 글 수정
            System.out.println("기존 게시글 수정: " + seq);
        } else {
            // 글 등록
            /**
             * 1. 글을 작성한 회원
             * 2. 게시판 설정
             * 3. gid
             * 4. 아이피 정보(ipAddr) & 브라우저 정보(요청 헤더 - User-Agent)
             */
            item = new BoardData();
            item.setBoard(board);
            item.setGid(gid);
            item.setMember(memberUtil.getMember());
            item.setIp(request.getRemoteAddr());
            item.setUa(request.getHeader("User-Agent"));
            System.out.println("새 게시글 등록");
        }

        // 등록, 수정 공통
        item.setCategory(form.getCategory());
        item.setPoster(form.getPoster());
        item.setSubject(form.getSubject());
        item.setContent(form.getContent());
        item.setSecret(form.isSecret());

        if (form.isGuest()) {
            item.setGuestPw(encoder.encode(form.getGuestPw()));
        }

        if (memberUtil.isAdmin()) {
            item.setNotice(form.isNotice());
        } else {
            item.setNotice(false); // 공지글은 관리자만 설정 가능
        }

        item.setPlainText(!board.isEditor());

        return item;
    }

    @Override
    public void afterProcess(BoardData item) {
        // 후처리 로직 (예: 파일 정리, 알림 발송 등)
        System.out.println("게시글 처리 완료: " + item.getSubject());
    }
}
