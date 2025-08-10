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


    /*
    * ModelMapper 빈 생성
    * 객체 간 매핑 시 STRICT을 적용하여 매핑 정확도를 높임
    */
    @Lazy
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return mapper;
    }


    /*
    * Jackson ObjectMapper 빈 생성
    * JSON 직렬화 / 역직렬화 처리용
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
    @Lazy
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }

    /*
     * JPAQueryFactory 빈 생성
     * QueryDSL 사용 시 편리하게 JPA 쿼리를 작성할 수 있도록 도와주는 팩토리 객체
     */
    @Lazy
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
