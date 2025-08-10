package xyz.vinllage.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
*
* 스프링 시큐리티 설정 클래스
* 웹 애플리케이션 보안 관련 설정
* 필요시 해당 클래스에서 로그인, 권한, 인증 등 보안 규칙 추가
*
*/
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // 현재 기본 설정 상태!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.build();
    }

}
