<<<<<<<< HEAD:src/main/java/xyz/vinllage/board_seul/post/controllers/RequestBoardData.java
package xyz.vinllage.board_seul.post.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.apache.tomcat.jni.FileInfo;
import xyz.vinllage.board_seul.controllers.BaseRequest_seul;
========
package xyz.vinllage.board.controllers;

>>>>>>>> 732bea3697cf9f1a318ad83857aff9f743725cf9:src/main/java/xyz/vinllage/board/controllers/RequestBoard.java

import java.util.List;

@Data
<<<<<<<< HEAD:src/main/java/xyz/vinllage/board_seul/post/controllers/RequestSeulBoardData_seul.java
public class RequestSeulBoardData_seul extends BaseRequest_seul {
========
public class RequestBoard {
    private String mode;
>>>>>>>> 732bea3697cf9f1a318ad83857aff9f743725cf9:src/main/java/xyz/vinllage/board/controllers/RequestBoard.java
    private Long seq;

    @NotBlank
    private String bid;

    @NotBlank
    private String gid;

    private String category;

    @NotBlank
    private String poster;
    private String guestPw;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;
    private boolean notice; // 공지글 여부
    private boolean secret; // 비밀글 여부

    private boolean guest; // 비회원 게시글 작성, 수정 여부

    private List<FileInfo> editorImages;
    private List<FileInfo> attachFiles;
}