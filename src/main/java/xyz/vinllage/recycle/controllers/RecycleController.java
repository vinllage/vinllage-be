package xyz.vinllage.recycle.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.vinllage.global.exceptions.BadRequestException;
import xyz.vinllage.global.libs.Utils;
import xyz.vinllage.global.search.ListData;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.libs.MemberUtil;
import xyz.vinllage.recycle.entities.DetectedRecycle;
import xyz.vinllage.recycle.services.DetectInfoService;
import xyz.vinllage.recycle.services.DetectSaveService;
import xyz.vinllage.recycle.services.RecycleInfoService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recycle")
@Tag(name="분리수거 API", description = "분리수거 데이터 저장/조회/수정/삭제 기능 제공")
public class RecycleController {
    private final DetectSaveService detectSaveService;
    private final DetectInfoService detectInfoService;
    private final RecycleInfoService recycleInfoService;
    private final MemberUtil memberUtil;
    private final Utils utils;


    @Operation(summary = "감지된 재활용 크롭 이미지 저장", method = "POST")
    @ApiResponse(responseCode = "201", description = "성공 시 201로 응답, 검증 실패시 400")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED) // 201로 응답
    public Map<String, Object> upload(
            @RequestPart("file") List<MultipartFile> files,
            @RequestPart("items") String itemsJson,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        Member loggedMember = memberUtil.getMember();
        DetectedRecycle recycle = detectSaveService.process(files, itemsJson, loggedMember);

        return Map.of(
                "gid", recycle.getGid(),
                "count", files.size(),
                "ids", files.stream().map(MultipartFile::getOriginalFilename).toList()
        );
    }

    @Operation(summary = "쓰레기 목록 조회", description = "page 기본값 1, limit 기본값 20")
    @GetMapping("/result")
    public ListData<DetectedRecycle> result(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "2362a2bc-1d87-4f66-bfdb-b7b42d991c02") String gid
    ) {
        return detectInfoService.getList(gid, page, limit);
    }
}