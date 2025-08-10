package xyz.vinllage.global.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/*
*
* 공통 Bean 설정 클래스
*
*/
@Configuration
public class BeanConfig {

    @PersistenceContext
    private EntityManager em;

    /**
     * ModelMapper 빈 등록
     * - DTO <-> Entity 변환 시 사용
     * - STRICT 매칭 전략 적용 -> 필드명이 정확히 일치할 때만 매핑
     * - 매핑 정확도를 높여 실수를 줄임
     *
     * @return
     */
    @Lazy
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    }

    /**
     * Jackson ObjectMapper 빈 등록
     * - JSON 직렬화 / 역직렬화 처리용
     * - JavaTimeModule 등록 -> LocalDate, LocalDateTime 등 날짜 타입 변환 가능
     * @return
     */
    @Lazy
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        return om;
    }

    /*
     * RestTemplate 빈 생성
     * 외부 REST API 호출 도구
     */

    /**
     * RestTemplate 빈 등록
     * - 외부 REST API 호출 도구
     * - HttpComponentsClientHttpRequestFactory 기반 -> 커넥션 관리, 타임아웃 설정 가능
     *
     * @return
     */
    @Lazy
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    /**
     * JPAQueryFactory 빈 등록
     * - QueryDSL 사용 시 편리하게 JPA 쿼리를 작성할 수 있도록 도와주는 팩토리 객체
     * - QueryDSL에서 타입 안전한 JPA 쿼리 작성 지원
     * - EntityManager를 기반으로 동작
     *
     * @return
     */
    @Lazy
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
