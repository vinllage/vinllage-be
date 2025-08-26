package xyz.vinllage.member.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.email.services.EmailSendService;
import xyz.vinllage.member.controllers.RequestToken;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;

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
     * 임시 비밀번호 생성
     *
     * @return 생성된 임시 비밀번호
     */
    private String generateTemporaryPassword() {
        return generateRandomPassword(PASSWORD_LENGTH);
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

    public void process(Member member) {
        // 1. 임시 비밀번호 생성 및 저장
        String tempPassword = generateTemporaryPassword();
        member.setTempPassword(encoder.encode(tempPassword));
        
        // 2. 임시 비밀번호 유효기간 설정 (5분)
        member.setTempPasswordExpiresAt(LocalDateTime.now().plusMinutes(5L));
        
        // 3. db에 적용
        memberRepository.saveAndFlush(member);

        // 4. 임시 비밀번호를 메일로 전송
        emailSendService.sendTemporaryPasswordEmail(member.getEmail(), tempPassword);
    }

    public boolean matchesPassword(String formPw, Member member) {
        return encoder.matches(formPw, member.getPassword());
    }

    public boolean matchesTempPassword(String formPw, Member member) {
        return encoder.matches(formPw, member.getTempPassword());
    }

    /**
     * 정상 로그인 -> 임시 비밀번호 제거
     *
     * @param form
     * @param member
     */
    public void deleteTempPassword(RequestToken form, Member member) {
        if (member.getTempPassword() != null && matchesPassword(form.getPassword(), member)) {
            member.setTempPassword(null);
            member.setTempPasswordExpiresAt(null);
            memberRepository.saveAndFlush(member);
        }
    }
}
