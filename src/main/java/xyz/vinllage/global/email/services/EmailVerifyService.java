package xyz.vinllage.global.email.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Lazy
@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private final RedisTemplate<String, String> redisTemplate;

    public String generateCode(String email) {
        String code = String.valueOf((int)(Math.random()*900000) + 100000);
        redisTemplate.opsForValue().set("email:" + email, code, 5, TimeUnit.MINUTES);
        return code;
    }

    public boolean verifyCode(String email, String inputCode) {
        String savedCode = redisTemplate.opsForValue().get("email:" + email);
        return savedCode != null && savedCode.equals(inputCode);
    }
}
