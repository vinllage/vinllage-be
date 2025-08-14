package xyz.vinllage.board.post.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vinllage.board.board.controllers.RequestBoard;
import xyz.vinllage.board.board.repositories.BoardRepository;
import xyz.vinllage.board.board.services.BoardInfoService;
import xyz.vinllage.board.controllers.BoardSearch;
import xyz.vinllage.board.post.entities.BoardData;
import xyz.vinllage.board.post.entities.QBoardData;
import xyz.vinllage.board.post.repositories.BoardDataRepository;
import xyz.vinllage.board.repositories.BaseRepository;
import xyz.vinllage.board.services.InfoService;
import xyz.vinllage.global.exceptions.*;
import xyz.vinllage.global.search.CommonSearch;

import java.time.LocalDate;
import java.util.List;

@Lazy
@Service
@Transactional
public class BoardDataInfoService extends InfoService<BoardData, Long>{

    private final BoardDataRepository repository;
    private final ModelMapper mapper;

    public BoardDataInfoService(HttpServletRequest request,
                            BoardDataRepository repository,ModelMapper mapper) {
        super(request);
        this.repository = repository;
        this.mapper=mapper;
    }

    @Override
    protected BaseRepository<BoardData, Long> getRepository() { return repository; }


    /**
     * 게시글 수정시 조회
     * @param seq
     * @return
     */
    public RequestBoard getForm(Long seq) {
        BoardData item = get(seq);
        RequestBoard form = mapper.map(item, RequestBoard.class);
        form.setBid(item.getBoard().getBid());
        return form;
    }

    @Override
    protected BooleanBuilder search(BoardSearch search) {
        /* 검색 조건 처리 S */
        String sopt = search.getSopt();
        String skey = search.getSkey();
        LocalDate sDate = search.getSDate();
        LocalDate eDate = search.getEDate();
        List<String> emails = search.getEmail();
        List<String> bids = search.getBid();

        BooleanBuilder andBuilder = new BooleanBuilder();
        QBoardData boardData = QBoardData.boardData;

        if (bids != null && !bids.isEmpty())  { // 게시판 아이디 조회
            andBuilder.and(boardData.board.bid.in(bids));
        }

        // 게시글 등록일 조회
        if (sDate != null) {
            andBuilder.and(boardData.createdAt.goe(sDate.atStartOfDay()));
        }

        if (eDate != null) {
            andBuilder.and(boardData.createdAt.loe(eDate.atTime(23, 59, 59)));
        }

        /**
         * 키워드 검색
         * sopt - ALL : 통합검색 (SUBJECT + CONTENT + NAME)
         *        SUBJECT : 게시글 제목
         *        CONTENT : 게시글 내용
         *        SUBJECT_CONTENT : 게시글 제목 + 내용
         *        NAME : 작성자명(poster) + 회원명(name) + 이메일(email)
         */
        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase() : "ALL";
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();

            StringExpression subject = boardData.subject;
            StringExpression content = boardData.content;
            StringExpression name = boardData.poster.concat(boardData.member.name)
                    .concat(boardData.member.email);

            StringExpression fields = null;
            if (sopt.equals("SUBJECT")) {
                fields = subject;
            } else if (sopt.equals("CONTENT")) {
                fields = content;
            } else if (sopt.equals("SUBJECT_CONTENT")) {
                fields = subject.concat(content);
            } else if (sopt.equals("NAME")) {
                fields = name;
            } else { // 통합검색
                fields = subject.concat(content).concat(name);
            }

            andBuilder.and(fields.contains(skey));
        }

        // 회원 이메일로 게시글 조회
        if (emails != null && !emails.isEmpty()) {
            andBuilder.and(boardData.member.email.in(emails));
        }

        /* 검색 조건 처리 E */

        return andBuilder;
    }
}
