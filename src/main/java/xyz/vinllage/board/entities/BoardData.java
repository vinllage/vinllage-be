package xyz.vinllage.board.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.global.entities.BaseEntity;
import xyz.vinllage.member.entities.Member;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(indexes = {
        @Index(name="idx_board_data_basic", columnList = "bid, notice DESC, createdAt DESC"),
        @Index(name="idx_baord_data_category", columnList = "bid, category, notice DESC, createdAt DESC")
})
public class BoardData extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length=45, nullable = false)
    private String gid;
