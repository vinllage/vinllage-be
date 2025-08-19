package xyz.vinllage.recycle.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.vinllage.recycle.services.DetectSaveService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recycle")
@Tag(name="Recycle API", description = "")
public class RecycleController {
    private final DetectSaveService detectSaveService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DetectSaveService.SaveResult> upload(
            @RequestPart("file") List<MultipartFile> files,
            @RequestPart("items") String itemsJson,
            // 커스텀 Principal에 맞게 memberId를 추출하도록 expression 수정하거나, 필요 없다면 인자 제거
            @AuthenticationPrincipal(expression = "member.seq") Long memberId
    ) {
        var result = detectSaveService.save(files, itemsJson, memberId);
        return ResponseEntity.ok(result);
    }
}
