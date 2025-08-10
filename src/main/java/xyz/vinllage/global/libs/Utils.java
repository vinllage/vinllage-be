package xyz.vinllage.global.libs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.aspectj.bridge.MessageUtil.getMessages;

/*
*
* 다국어 지원 설정 X, 추후 필요시 추가
*
*/
@Component
@RequiredArgsConstructor
public class Utils {
    private final HttpServletRequest request;

    public Map<String, List<String>> getErrorMessages(Errors errors) {
        // 필드별 검증 실패 메세지  - rejectValue, 커맨드 객체 검증(필드)
        Map<String, List<String>> messages = errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, f -> List.of(f.getDefaultMessage()), // 기본 메세지만 출력
                        (v1, v2) -> v2));

        return messages;
    }

    public String getUrl(String url) {
        String protocol = request.getScheme(); // http, https 등 프로토콜
        String domain = request.getServerName(); // 도메인 이름 또는 IP
        int _port = request.getServerPort(); // 포트 번호
        // 80, 443 포트는 URL에 표시하지 않음
        String port = List.of(80, 443).contains(_port) ? "" : ":" + _port;

        // 전체 URL 구성 (프로토콜://도메인[:포트]/컨텍스트경로/url)
        return String.format("%s://%s%s%s",
                protocol, domain, port, request.getContextPath(), url);
    }
}
