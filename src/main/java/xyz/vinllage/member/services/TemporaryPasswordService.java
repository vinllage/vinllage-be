package xyz.vinllage.member.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.email.services.EmailSendService;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Lazy
@Service
@RequiredArgsConstructor
public class TemporaryPasswordService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12; // 임시 비번 길이 (12자 추천)
    private final MemberRepository memberRepository;
    private final EmailSendService emailSendService;
    private final PasswordEncoder encoder;

    /**
     * 임시 비밀번호 생성 및 Redis 저장
     * @param email 사용자 이메일
     * @return 생성된 임시 비밀번호
     */
    private String generateTemporaryPassword(String email) {
        String tempPassword = generateRandomPassword(PASSWORD_LENGTH);
        redisTemplate.opsForValue().set("tempPwd:" + email, tempPassword, 10, TimeUnit.MINUTES);
        return tempPassword;
    }

    /**
     * 입력한 비밀번호가 Redis에 저장된 임시 비밀번호와 일치하는지 검증
     * @param email 사용자 이메일
     * @param inputPassword 사용자가 입력한 임시 비밀번호
     * @return 일치 여부
     */
    public boolean verifyTemporaryPassword(String email, String inputPassword) {
        String savedPassword = redisTemplate.opsForValue().get("tempPwd:" + email);
        return savedPassword != null && savedPassword.equals(inputPassword);
    }

    /**
     * 보안 강화를 위한 랜덤 비밀번호 생성
     */
    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }

        return sb.toString();
    }

    public void process(String email) {
        // 임시 비번 생성 + 저장 + 이메일 발송
        String tempPassword = generateTemporaryPassword(email);

        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member != null) {
            member.setPassword(encoder.encode(tempPassword));         // 임시 비밀번호 변경
            memberRepository.saveAndFlush(member);
        }

        emailSendService.sendTemporaryPasswordEmail(email, tempPassword);  // 메일 전송
    }
}
