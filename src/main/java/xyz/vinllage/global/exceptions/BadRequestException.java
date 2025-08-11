package xyz.vinllage.global.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException {
    public BadRequestException() {
      super("BadRequest", HttpStatus.BAD_REQUEST);
      setErrorCode(true);
    }

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    // 에러가 발생한 위치, 에러 메세지들을 Map 형태로 받아와서 묶어
    public BadRequestException(Map<String, List<String>> errorMessages) {
        super(errorMessages, HttpStatus.BAD_REQUEST);
    }
}
