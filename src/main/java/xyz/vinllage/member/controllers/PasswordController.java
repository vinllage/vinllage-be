package xyz.vinllage.member.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.global.validators.Password;

import java.util.Map;

@RestController
@RequestMapping("/api/password")
@RequiredArgsConstructor
public class PasswordController {
    private final Password password;

    @GetMapping("/check")
    public Map<String , Object> check(@RequestBody String Password){

        return null; // 낼 백엔드 완료 하겠습니다 강사님 한게 며쭈어 보고 요
    }
}
