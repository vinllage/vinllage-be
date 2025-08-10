package xyz.vinllage.global.libs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
*
* 다국어 지원 설정 X, 추후 필요시 추가
*
*/
@Component
@RequiredArgsConstructor
public class Utils {

    // 현재 요청(request) 정보를 담은 객체
    // 프로토콜, 도메인, 포트, 요청 경로 등 조회 가능
    private final HttpServletRequest request;

    /**
     * 필드 검증 실패 메시지 추출
     * - Spring Validation(Errors) 객체에서 필드별 오류 메시지를 Map 형태로 변환
     * - key   : 필드명
     * - value : 해당 필드의 검증 실패 메시지 목록 (여기서는 기본 메시지만)
     *
     * @param errors
     * @return
     */
    public Map<String, List<String>> getErrorMessages(Errors errors) {
        // 필드별 검증 실패 메세지  - rejectValue, 커맨드 객체 검증(필드)
        Map<String, List<String>> messages = errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,         // key: 필드명
                        f -> List.of(f.getDefaultMessage()),    // value: 기본 메시지 1개
                        (v1, v2) -> v2));           // 중복 키가 있을 경우, 나중 값으로 덮어씀

        return messages;
    }

    /**
     * 요청 기반의 절대 URL 생성
     *
     * 예:
     *  - 입력: "/uploads/image.jpg"
     *  - 결과: "http://localhost:4000/uploads/image.jpg"
     *
     * @param url
     * @return
     */
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
