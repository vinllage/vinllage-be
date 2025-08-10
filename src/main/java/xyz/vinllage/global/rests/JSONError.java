package xyz.vinllage.global.rests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/*
*
* API 응답 시 에러 정보를 JSON 형태로 담는 클래스
*
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JSONError {
    private HttpStatus status;
    private Object messages;
}
