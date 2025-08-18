<<<<<<<< HEAD:src/main/java/xyz/vinllage/board_seul/comment/controllers/RequestComment.java
package xyz.vinllage.board_seul.comment.controllers;
========
package xyz.vinllage.board.controllers;
>>>>>>>> 732bea3697cf9f1a318ad83857aff9f743725cf9:src/main/java/xyz/vinllage/board/controllers/RequestComment.java

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import xyz.vinllage.board_seul.controllers.BaseRequest_seul;

@Data
public class RequestSeulComment extends BaseRequest_seul {
    private String mode;
    @NotNull
    private Long boardDataSeq; // 게시글 번호
    private Long seq; // 댓글 seq

    @NotBlank
    private String commenter; // 댓글 작성자

    private String guestPw; // 비회원 글수정, 글삭제 비밀번호

    @NotBlank
    private String content; // 댓글 내용

    private boolean guest; // 비회원 댓글 여부
}