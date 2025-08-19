package xyz.vinllage.recycle.entities;

import jakarta.persistence.*;
import lombok.Data;
import xyz.vinllage.global.entities.BaseEntity;
import xyz.vinllage.member.entities.Member;

@Data
@Entity
public class DetectedRecycle extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "file_seq", nullable = false)
    private Long fileSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 길이 고려: TEXT 또는 DB가 지원하면 JSON 타입
    @Column(columnDefinition = "TEXT") // MySQL JSON 쓰면 "JSON"
    private String data; // JSON 문자열 원본
}
