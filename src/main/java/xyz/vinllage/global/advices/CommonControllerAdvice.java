package xyz.vinllage.global.advices;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.vinllage.global.exceptions.CommonException;
import xyz.vinllage.global.libs.Utils;
import xyz.vinllage.global.rests.JSONError;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice("xyz.vinllage") // 지정된 패키지 ("xyz.vinllage") 범위의 @RestController에서 발생하는 예외를 한 곳에서 처리
public class CommonControllerAdvice {
    private final Utils utils;

    /**
     * 모든 예외(Exception.class)를 처리하는 핸들러
     * - 발생한 예외를 잡아 HTTP 상태 코드와 JSON 에러 응답을 반환
     * - 프로젝트 전역 예외 처리용
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONError> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본 HTTP 상태: 500 (서버 에러)
        Object message = e.getMessage(); // 예외 객체 기본 메시지

        /**
         * 1. 프로젝트에서 정의한 공통 예외(CommonException) 처리
         * - 예외에 지정된 상태 코드와 메시지 사용
         * - 필드 검증 실패 메시지가 있으면 해당 메시지를 반환
         * - 에러 코드 형태의 메시지가 있다면 코드 그대로 반환
         */
        if (e instanceof CommonException commonException) {
            status = commonException.getStatus(); // 예외에 지정된 HTTP 상태 코드 사용
            Map<String, List<String>> errorMessages = commonException.getErrorMessages();
            if (errorMessages != null) {
                // 검증 실패 (FieldError) 메시지가 있는 경우
                message = errorMessages;
            } else if (commonException.isErrorCode()) {
                message = message.toString(); // 에러 코드를 그대로 메세지로 사용
            }
        }
        /**
         * 2. Spring Security 권한 부족 예외 처리
         * - 인증/인가 실패 시 발생
         * - HTTP 상태 코드 401 + 고정 메시지 "UnAuthorized"
         */
        else if (e instanceof AuthorizationDeniedException) {
            status = HttpStatus.UNAUTHORIZED; // 401
            message = "UnAuthorized"; // 고정 문자열 사용
        }

        e.printStackTrace();

        return ResponseEntity.status(status).body(new JSONError(status, message)); // JSON 형식으로 응답 반환
    }
}
