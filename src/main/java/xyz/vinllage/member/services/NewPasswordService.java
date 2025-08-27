package xyz.vinllage.member.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.email.sevices.EmailSendService;
import xyz.vinllage.member.controllers.RequestToken;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@Lazy
@RequiredArgsConstructor
public class NewPasswordService {
    private final RedisTemplate<String, String> redisTemplate;
    private final MemberRepository repository;
    private final EmailSendService service;
    private PasswordEncoder encoder;
    private static final int PASSWORD_LENGTH= 12; // 임시 비밀 번호 길이 (추추천
    private static String CHAR_SET= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 임시 비밀 번호 생성밎 redis 저장
     */
    public String getTemporary(){
        return PasswordRandom(PASSWORD_LENGTH);
    }

    public String PasswordRandom(int length){
        SecureRandom random = new SecureRandom();
        StringBuffer stringBuffer=  new StringBuffer();
        for(int i =0;  i < length; i++){
            stringBuffer.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return stringBuffer.toString();
    }
    public void process(Member member){
        // 1. 임시 비밀 번호 생성 밎 저장
        String tPassword = getTemporary();
        member.setPassword(encoder.encode(tPassword));
        // 2.  임시 비밀 번호 유호 기간 설정 (2분)
        member.setTempPasswordExpiresAt(LocalDateTime.now().plusMonths(2L));
        // 3. db 에 적용
        repository.saveAndFlush(member);
        //4. 임시 비밀 번호를 메일로 전송
        service.sendTemporaryPassword(member.getEmail(), member.getTempPassword());
    }
    public boolean matchesPassword(String formPw , Member member){
        return encoder.matches(formPw, member.getPassword());
    }
    public boolean matchesTempPassword(String formPw, Member member){
        return encoder.matches(formPw, member.getTempPassword());
    }

    /**
     * 정상 로그인 -> 임시 비밀 번호 제거
     * @param form
     * @param  member
     */
    public  void delete(RequestToken form , Member member){
        if(member.getTempPassword() != null && matchesPassword(form.getPassword(), member)){
            member.setTempPassword(null);
            member.setTempPassword(null);
            member.setTempPasswordExpiresAt(null);
            repository.saveAndFlush(member);
        }
    }
}
