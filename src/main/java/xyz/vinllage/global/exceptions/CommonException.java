package xyz.vinllage.global.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

/*
*
* 프로젝트 전반에서 사용할 공통 예외 클래스
*
*/
@Getter
@Setter
public class CommonException extends RuntimeException {
    private final HttpStatus status;
    private Map<String, List<String>> errorMessages;
    private boolean errorCode;

    // 메시지와 상태 코드를 받는 생성자
    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    // 필드별 에러 메시지와 상태 코드를 받는 생성자
    public CommonException(Map<String, List<String>> errorMessages, HttpStatus status) {
        this.errorMessages = errorMessages;
        this.status = status;
    }

}
