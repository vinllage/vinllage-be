package xyz.vinllage.global.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/*
* application.yml에 적힌 file.upload로 시작하는 설정 값을 읽어서 저장
* url: 웹에서 파일에 접근할 때 사용하는 주소
* path: 서버 컴퓨터 안에서 파일이 실제로 저장된 위치
* */
@Data
@ConfigurationProperties(prefix = "file.upload")
public class FileProperties {
    private String url; // ex) file.upload.url=/uploads
    private String path; // ex) file.upload.path=C:/files/uploads
}
