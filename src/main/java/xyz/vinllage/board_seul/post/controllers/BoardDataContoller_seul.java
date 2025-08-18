package xyz.vinllage.board_seul.post.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.board_seul.post.entities.BoardData_Seul;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Tag(name="게시판 구현 API", description = "게시글 작성, 수정, 조회, 삭제 등의 기능을 제공")
public class BoardDataContoller_seul {

    @Operation(summary = "게시글 한개 조회", method = "GET", description = "경로변수 형태로 게시글 조회, /api/v1/board/info/게시글번호 형식으로 조회 요청")
    @ApiResponse(responseCode = "200", description = "게시글 한개")
    @Parameter(name="seq", required = true, in= ParameterIn.PATH, description = "게시글 등록번호")
    @GetMapping("/info/{seq}")
    public BoardData_Seul info(@PathVariable("seq") Long seq, Model model) {
        commonProcess(seq, "view", model);

        return infoService.get(seq);
    }
}
