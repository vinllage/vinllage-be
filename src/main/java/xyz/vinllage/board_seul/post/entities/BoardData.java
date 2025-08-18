package xyz.vinllage.board_seul.post.entities;

import jakarta.persistence.*;
import lombok.Data;

import xyz.vinllage.board_seul.board.entities.Board;
import xyz.vinllage.board_seul.entities.BoardEntity;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.global.entities.BaseEntity;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.entities.Member;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(indexes = {
        @Index(name="idx_board_data_basic", columnList = "bid, notice DESC, createdAt DESC"),
        @Index(name="idx_baord_data_category", columnList = "bid, category, notice DESC, createdAt DESC")
})
public class BoardData extends BoardEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length=45, nullable = false)
    private String gid;

    @JoinColumn(name="bid")
    @ManyToOne(fetch= FetchType.LAZY)
    private Board board;

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
        return this.board.getCommentAuthority();
    }
}