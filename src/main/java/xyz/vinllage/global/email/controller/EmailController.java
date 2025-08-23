package xyz.vinllage.global.email.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.vinllage.global.email.sevices.EmailSendService;
import xyz.vinllage.global.email.sevices.EmailVerifyService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class EmailController {
    private final EmailSendService sendService;
    private final EmailVerifyService verifyService;

    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@RequestParam String email) {
        String code = verifyService.generateCode(email);
        sendService.sendVerificationEmail(email, code);
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
