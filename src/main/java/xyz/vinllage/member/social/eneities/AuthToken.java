package xyz.vinllage.member.social.eneities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthToken {

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private long expiresIn;

    private long refreshTokenExpiresIn; // 인증 시스템에서 사용자의 로그인 상태를 유지 하기 유지 하기 사용 하는 토큰





}
