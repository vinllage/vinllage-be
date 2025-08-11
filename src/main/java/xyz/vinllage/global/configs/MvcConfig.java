package xyz.vinllage.global.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
*
* MVC 기능 활성화
* 1. @Configuration: 이 클래스는 스프링 설정 클래스임을 명시 (스프링 컨테이너가 빈으로 등록하여 관리)
* 2. @EnableJpaAuditing: JPA Auditing 기능("BaseEntity"에서 사용될 @CreatedDate, @LastModifiedDate 등) 활성화
* 3. @EnableScheduling: @Scheduled로 메서드를 주기적으로 실행할 수 있는 스케줄링 기능 활성화
*
*/
@Configuration
@EnableJpaAuditing
@EnableScheduling
public class MvcConfig implements WebMvcConfigurer {
}
