package xyz.vinllage.board_seul.post.entities;

import jakarta.persistence.*;
import lombok.Data;

import xyz.vinllage.board_seul.board.entities.Board_seul;
import xyz.vinllage.board_seul.entities.BoardEntity_seul;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.member.constants.Authority;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(indexes = {
        @Index(name="idx_board_data_basic", columnList = "bid, notice DESC, createdAt DESC"),
        @Index(name="idx_baord_data_category", columnList = "bid, category, notice DESC, createdAt DESC")
})
public class BoardData_Seul extends BoardEntity_seul implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length=45, nullable = false)
    private String gid;

    @JoinColumn(name="bid")
    @ManyToOne(fetch= FetchType.LAZY)
    private Board_seul boardSeul;

    @Column(length=60)
    private String category;

    @Column(nullable = false)
    private String subject;

    private boolean notice;
    private boolean secret;
    private int viewCount;
    private int commentCount;
    private boolean plainText;

    @Transient
    private List<FileInfo> editorImages;

    @Transient
    private List<FileInfo> attachFiles;

    @Transient
    private boolean editable;

    public Authority getCommentAuthority() {
        return this.boardSeul.getCommentAuthority();
    }
}