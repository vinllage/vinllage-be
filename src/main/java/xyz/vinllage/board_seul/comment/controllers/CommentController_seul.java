package xyz.vinllage.board_seul.comment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.board_seul.comment.entities.Comment_seul;
import xyz.vinllage.board_seul.comment.services.CommentDeleteService_seul;
import xyz.vinllage.board_seul.comment.services.CommentInfoService_seul;
import xyz.vinllage.board_seul.comment.services.CommentPermissionService_seul;
import xyz.vinllage.board_seul.controllers.BoardSearch_seul;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController_seul {

    private final CommentInfoService_seul infoService;
    private final CommentDeleteService_seul deleteService;
    private final CommentPermissionService_seul permissionService;

    @GetMapping("/list/{boardDataSeq}")
    public List<Comment_seul> getComments(@PathVariable Long boardDataSeq) {
        BoardSearch_seul search = new BoardSearch_seul();
        search.setSeq(boardDataSeq);
        return infoService.getList(search).getItems();
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<String> deleteComment(@PathVariable Long seq) {
        deleteService.delete(seq);
        return ResponseEntity.ok("댓글이 삭제되었습니다.");
    }
}
