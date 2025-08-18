package xyz.vinllage.board_seul.post.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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


}
