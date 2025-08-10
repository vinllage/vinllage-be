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
@RestControllerAdvice("xyz.vinllage") // 지정된 패키지 ("xyz.vinllage") 범위의 컨트롤러에서 발생하는 예외를 한 곳에서 처리
public class CommonControllerAdvice {
    private final Utils utils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONError> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본 상태 코드: 500
        Object message = e.getMessage(); // 예외 기본 메시지

        // 1. 프로젝트에서 정의한 예외 처리
        if (e instanceof CommonException commonException) {
            status = commonException.getStatus(); // 예외에 지정된 HTTP 상태 코드 사용
            Map<String, List<String>> errorMessages = commonException.getErrorMessages();
            if (errorMessages != null) {
                // 검증 실패 (FieldError) 메시지가 있는 경우
                message = errorMessages;
            } else if (commonException.isErrorCode()) {
                    message = message.toString(); // 에러 코드를 그대로 메세지로 사용
                }

        // 2. 권한 인증 / 인가 실패 예외 처리
        //  spring Security에서 권한 부족 시 발생
        } else if (e instanceof AuthorizationDeniedException) {
            status = HttpStatus.UNAUTHORIZED; // 401
            message = "UnAuthorized"; // 고정 문자열 사용
        }

        e.printStackTrace();

        return ResponseEntity.status(status).body(new JSONError(status, message)); // JSON 형식으로 응답 반환
    }
}
