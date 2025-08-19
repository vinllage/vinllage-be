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
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.libs.MemberUtil;
import xyz.vinllage.recycle.services.DetectSaveService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recycle")
@Tag(name="Recycle API", description = "")
public class RecycleController {
    private final DetectSaveService detectSaveService;
    private final MemberUtil memberUtil;
    private final Utils utils;

    @Operation(summary = "감지된 재활용 크롭 이미지 저장", method = "POST")
    @ApiResponse(responseCode = "201", description = "성공 시 201로 응답, 검증 실패시 400")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED) // 201로 응답
    public void upload(
            @RequestPart("file") List<MultipartFile> files,
            @RequestPart("items") String itemsJson,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        Member loggedMember = memberUtil.getMember();
        detectSaveService.process(files, itemsJson, loggedMember);
    }
}
