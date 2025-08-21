package xyz.vinllage.mypage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.file.entities.FileInfo;
import xyz.vinllage.file.services.FileInfoService;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage/recycle")
@Tag(name = "마이페이지 리사이클 API", description = "내 리사이클(detect) 이미지 목록")
public class MyPageRecycleController {

    private final MemberUtil memberUtil;
    private final FileInfoService fileInfoService;


    /**
     *
     * (마이페이지) 내 리사이클(detect) 이미지 목록
     *      - gid: 로그인 회원 seq (문자열)
     *      - location: "detect"
     *      - 상태: 기본 DONE (FileInfoService.getList(gid, loc) 가 DONE 만 반환)
     *      - addInfo(): FileInfoService 내부에서 이미 처리됨
     * @return
     */
    @GetMapping("/files")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "내 리사이클 이미지 목록")
    public List<FileItemDto> myRecycleFiles() {
        Member me = memberUtil.getMember();         // 로그인 보장
        String gid = String.valueOf(me.getSeq());   // 내 파일만 보도록 회원번호를 gid로 사용

        List<FileInfo> items = fileInfoService.getList(gid, "detect"); // DONE 상태만
        return items.stream().map(FileItemDto::from).toList();
    }

    public record FileItemDto(
            Long seq,
            String fileUrl,
            String thumbBaseUrl,
            String originalName,
            boolean image
    ) {
        public static FileItemDto from(FileInfo f) {
            return new FileItemDto(
                    f.getSeq(),
                    f.getFileUrl(),
                    f.getThumbBaseUrl(),  // 이미지인 경우에만 값이 채워짐
                    f.getFileName(),
                    f.isImage()
            );
        }
    }
}
