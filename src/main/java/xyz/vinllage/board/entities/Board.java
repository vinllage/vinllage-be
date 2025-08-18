package xyz.vinllage.board.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.util.StringUtils;
import xyz.vinllage.global.entities.BaseEntity;
import xyz.vinllage.member.constants.Authority;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Board extends BaseEntity implements Serializable {
    @Id
    @Column(length=45)
    private String bid; // 게시판 아이디

    @Column(length=100, nullable = false)
    private String name; // 게시판 이름

    private int rowsForPage; // 한페이지당 레코드 갯수

    private int pageCount; // 노출될 페이지 갯수

    @Column(length=45, nullable = false)
    private String skin; // 게시판 스킨

    @Lob
    private String category; // 게시판 분류

    private boolean active; // 게시판 사용 여부, true - 사용, false : 미사용
    private boolean editor;  // 에디터 사용 여부
    private boolean imageUpload; // 에디터에 이미지 추가 기능 사용 여부
    private boolean attachFile; // 파일 첨부 기능 사용 여부
    private boolean comment; // 댓글 사용 여부
    private boolean afterWritingRedirect; // 글 작성 후 이동 방향 - false : 게시글 목록,  true : 게시글 상세
    private boolean showViewList; // 글보기 하단에 목록 노출 여부

    @Enumerated(EnumType.STRING)
    private Authority listAuthority; // 목록 권한, ALL - 전체, MEMBER - 회원, ADMIN - 관리자
