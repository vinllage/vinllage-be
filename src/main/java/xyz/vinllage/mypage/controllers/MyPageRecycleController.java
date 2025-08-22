package xyz.vinllage.mypage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.services.DetectInfoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage/recycle")
@Tag(name = "마이페이지 리사이클 API", description = "내 리사이클(detect) 결과 이미지 목록")
public class MyPageRecycleController {

    private final DetectInfoService detectInfoService;

    @Operation(summary = "내 리사이클 이미지 목록 조회", method = "GET")
    @ApiResponse(responseCode = "200")
    @GetMapping("/files")
    public ListData<DetectedRecycle> myRecycleFiles(
            @RequestParam(defaultValue = "e0362325-5c16-480e-93d2-373c6d107f3d") String gid,  // 프론트에서 gid 직접 넘김
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return detectInfoService.getList(gid, page, limit);
    }
}
