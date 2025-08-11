package xyz.vinllage.global.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/*
* application.yml에 적힌 file.upload로 시작하는 설정을 자동으로 바인딩
* - file.upload.url 값(/uploads)을 -> this.url로 자동 주입
* - file.upload.path 값(C:/files/uploads)을 -> this.path로 자동 주입
*
* [목적]
* 파일 업로드 관련 경로를 하드코딩하지 않고 설정 파일에서 관리하기 위함
*
* url: 웹에서 파일에 접근할 때 사용하는 주소
* path: 서버 컴퓨터 안에서 파일이 실제로 저장된 위치
*
*/
@Data
@ConfigurationProperties(prefix = "file.upload")
public class FileProperties {
    private String url;
    private String path;
}
