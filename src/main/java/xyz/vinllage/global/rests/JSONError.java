package xyz.vinllage.global.rests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/*
*
* API 에러 응답을 JSON 형식으로 표현하기 위한 DTO 클래스
*
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONError {
    private HttpStatus status;  // HTTP 상태 코드 (예: 400, 404, 500 등)
    private Object messages;    // 에러 메시지 (문자열-단일 에러 메시지 or Map 형태-필드별 검증 실패 메시지 목록)
}
