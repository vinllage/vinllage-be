package xyz.vinllage.board_seul.board.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Tag(name="게시판 구현 API", description = "게시글 작성, 수정, 조회, 삭제 등의 기능을 제공")
public class BoardController_seul {
}
