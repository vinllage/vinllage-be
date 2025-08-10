package xyz.vinllage.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
* 파일 업로드/다운로드 관련 설정 클래스
* 외부 저장된 파일(이미지, 문서 등)을 웹에서 접근할 수 있도록 설정
*
* [목적]
* 업로드된 파일(이미지, 문서 등)을 서버 로컬 저장소에서 직접 서빙할 수 있도록 설정
* - 컨트롤러 코드 작성 없이 URL만으로 파일 접근 가능
* - 운영환경/로컬환경에 따라 경로 변경이 용이 (yml 수정만 하면 됨)
*/
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(FileProperties.class)
public class FileConfig implements WebMvcConfigurer {

    // application.yml 등에서 설정한 파일 관련 경로, URL 정보를 가진 객체
    private final FileProperties properties;

    // 웹에서 "/uploads/**"로 요청이 들어오면, "file:///C:/files/uploads/"에 저장된 파일을 찾아서 보여줌
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(properties.getUrl() + "/**")
                .addResourceLocations("file:///" + properties.getPath() + "/");  // file:/// - 인터넷에 있는 게 아니라, 내 컴퓨터(서버)에 있는 파일임을 스프링한테 알림
    }
}
