package xyz.vinllage.global.email.sevices;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import xyz.vinllage.global.email.entities.EmailEntiry;
import xyz.vinllage.global.libs.Utils;

import java.util.HashMap;
import java.util.Map;

@Lazy
@Service
@RequiredArgsConstructor
public class EmailVerifyService {
    private final xyz.vinllage.global.email.sevices.EmailSendService service;
    private final HttpSession session;
    private final Utils utils;
    // 인증 번호 생성
    public boolean sendCode(String email){
        int authNum = (int)(Math.random() * 999999);
        // 새션 속성 값에 생성한 인증 번호 저장
        session.setAttribute("EmailAutNum" , authNum);
        // 세션 속성값에 현재 시간 값의 저장 (인증 시간 제한을 두기 위한)
        session.setAttribute("EmailAuthNum", System.currentTimeMillis());
        // 공통메세지 관리에서 제목과 메일 내용을 가져와 메세지 vo 값에 대입
        // 회원 가입 이메일 인증 메일 입니다 | 발급된 인증 코드를 회원 가입 목록에 입력하세요
        EmailEntiry emailEntiry = new EmailEntiry(
                email,
                utils.getMessage("Email.verification.subject"), // 작성자
                utils.getMessage("Email.verification.message"));// 내용
            // 이메일은 템플릿 형태로 참고 해서 전송
        Map<String , Object> tpl = new HashMap<>();
        tpl.put("authNum", authNum);
        return service.sendMail(emailEntiry, "auth", tpl);
    }
    // 인증번호 일치 여부 체크
    public  boolean check(int code){
        Integer authNum = (Integer) session.getAttribute("EmailAuthNum");
        Long stime = (Long)session.getAttribute("EmailAuthStart");
        // 인증시간 만료 여부s
        if(authNum != null && stime != null){ // 만료되 었다면 세션 비우고 검증 실패 처리
            boolean is =(System.currentTimeMillis() - stime.longValue())> 10000 * 60 * 3;
            if(is){ // 만료 되었다면 세션 비우고 검증 실패 처리
                session.removeAttribute("EmailAuthNum");
                session.removeAttribute("EmailAuthStart");
                return false;
            }
            /* 인증 시간 만료 여부 채크e */
            // 사용자 입력 코드와 발급 코드가 일치 하는지 여부 체크
            boolean isVerified  = code == authNum.intValue();
            session.setAttribute("EmailAuthVerified", isVerified );
            return isVerified;
        }
        return false;
    }
}
