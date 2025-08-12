package xyz.vinllage.member.social.services;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import xyz.vinllage.global.libs.Utils;
import xyz.vinllage.member.repositories.MemberRepository;
import xyz.vinllage.member.services.MemberInfoService;

@Lazy
@Service
@RequiredArgsConstructor
public class KaKaoLoginService implements SocialLoginService{
    private final Utils utils;
    private final HttpSession session;
    private final RestTemplate restTemplate;
    private final MemberInfoService service;
    private final MemberRepository repository;
    @Value("${social.kakao.apikey}")
    private String apikey;

    @Override
    public String getToken(String code) {
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String > body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", apikey);
        body.add("redirect_url", "");



        return "";
    }

    @Override
    public boolean login(String token) {
        return false;
    }

    @Override
    public boolean exists(String token) {
        return false;
    }

    @Override
    public String getLoginUrl(String redirectUrl) {
        return "";
    }
}
