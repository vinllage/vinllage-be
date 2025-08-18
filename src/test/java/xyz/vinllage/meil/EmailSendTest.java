package xyz.vinllage.meil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.vinllage.global.email.entities.EmailMessage;
import xyz.vinllage.global.email.service.EmailSendService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailSendTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    void sendTest(){
        EmailMessage message = new EmailMessage("songjaese@gmail.com", "제목", "내용");
        boolean success = emailSendService.sendMail(message);
        assertTrue(success);
    }

    @Test
    @DisplayName("템플릿 형태로 전송 테스트")
    void sendWithTpltest(){
        EmailMessage message = new EmailMessage("songjaese@gmail.com", "제목", "내용");
        Map<String, Object> tplData = new HashMap<>();
        tplData.put("duthNum", "12345678");
        boolean success = emailSendService.sendMail(message, "auth", tplData);
        assertTrue(success);
    }
}
