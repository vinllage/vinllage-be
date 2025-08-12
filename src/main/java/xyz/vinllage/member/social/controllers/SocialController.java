package xyz.vinllage.member.social.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.member.social.constants.SocialType;
import xyz.vinllage.member.social.services.KaKaoLoginService;
import xyz.vinllage.member.social.services.NaverLoginService;
import xyz.vinllage.member.social.services.SocialLoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/social")
public class SocialController {
    private final HttpSession session;
    private final KaKaoLoginService kaKaoLoginService;
    private final NaverLoginService naverLoginService;

    @GetMapping("/callback/{channel}")
    public String callback(@PathVariable("channel")String  ype , @RequestParam("") String code , @RequestParam(name = "start", required = false) String redirectUrl ){
        SocialType socialType = SocialType.valueOf(type.toUpperCase());

        SocialLoginService service = socialType == SocialType.NAVER ? naverLoginService : kaKaoLoginService;

        // 토큰(Access 토큰 x 유저 구분을 위한 토큰 발급)  발급

        String token = service.getToken(code);
        // 로그인 처리
        if(service.login(token)){
            // 로그인 성공시에 redrectUrl 또는 메인 페이지로 이동
            return "redirect:" +(StringUtils.hasText(redirectUrl) ? redirectUrl : "/");
        }
        // 로그인 실패시에 아직 소셜 회원으로 가입 된것이 아니므로 가입 화면 이동
        session.setAttribute("socialType", socialType);
        session.setAttribute("socialToken", token);

        return null;

    }

}
