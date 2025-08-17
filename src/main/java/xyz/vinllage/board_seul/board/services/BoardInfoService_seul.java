package xyz.vinllage.board_seul.board.services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vinllage.board_seul.board.controllers.RequestBoard;
import xyz.vinllage.board_seul.board.entities.Board;
import xyz.vinllage.board_seul.board.entities.QBoard;
import xyz.vinllage.board_seul.board.repositories.BoardRepository;
import xyz.vinllage.board_seul.controllers.BoardSearch;
import xyz.vinllage.board_seul.repositories.BaseRepository;
import xyz.vinllage.board_seul.services.InfoService;

@Lazy
@Service
@Transactional
public class BoardInfoService_seul extends InfoService<Board, String> {

    private final BoardRepository repository;
    private final ModelMapper mapper;

    public BoardInfoService_seul(HttpServletRequest request,
                                 BoardRepository repository,
                                 ModelMapper mapper) {  // mapper 추가!
        super(request);
        this.repository = repository;
        this.mapper = mapper;  // 초기화!
    }

    @Override
    protected BaseRepository<Board, String> getRepository() {
        return repository;
    }

    @Override
    protected BooleanBuilder search(BoardSearch search) {
        BooleanBuilder andBuilder = new BooleanBuilder();
        QBoard board = QBoard.board;

        String sopt = search.getSopt();
        String skey = search.getSkey();

        // 키워드 검색 처리 S
        sopt = StringUtils.hasText(sopt) ? sopt.toUpperCase() : "ALL";
        if (StringUtils.hasText(skey)) {
            skey = skey.trim();

            StringExpression fields = null;
            if (sopt.equals("BID")) {
                fields = board.bid;
            } else if (sopt.equals("NAME")) {
                fields = board.name;
            } else { // 통합 검색 BID + NAME
                fields = board.bid.concat(board.name);
            }

            andBuilder.and(fields.contains(skey));
        }

        return andBuilder;
    }

    public RequestBoard getForm(String bid) {
        Board board = get(bid);

        return mapper.map(board, RequestBoard.class);
    }

}
