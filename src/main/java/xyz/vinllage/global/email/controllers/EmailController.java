package xyz.vinllage.global.email.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.global.email.dtos.RequestEmail;
import xyz.vinllage.global.email.services.EmailSendService;
import xyz.vinllage.global.email.services.EmailVerifyService;
import xyz.vinllage.global.libs.Utils;
import xyz.vinllage.global.validators.EmailAuthValidator;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmailController {
    private final Utils utils;
    private final EmailAuthValidator emailAuthValidator;
    private final EmailSendService sendService;
    private final EmailVerifyService verifyService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@Valid @RequestBody RequestEmail request, Errors errors) {
        // 이메일 중복 검증
        emailAuthValidator.validate(request, errors);

        if (errors.hasErrors()) {
            System.out.println("에러있음!");
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("messages", utils.getErrorMessages(errors)));
        }

        String code = verifyService.generateCode(request.getEmail());
        sendService.sendVerificationEmail(request.getEmail(), code);
        return ResponseEntity.ok("인증 코드 전송 완료");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code) {
        if (verifyService.verifyCode(email, code)) {
            return ResponseEntity.ok("인증 성공");
        }
        return ResponseEntity.badRequest().body("인증 실패");
    }
}
