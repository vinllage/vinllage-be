package xyz.vinllage.meil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import xyz.vinllage.global.email.entities.EmailEntiry;
import xyz.vinllage.global.email.sevices.EmailSendService;
import xyz.vinllage.global.email.sevices.EmailVerifyService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailSendTest {

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private EmailVerifyService service;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void sendTest(){
        EmailEntiry message = new EmailEntiry("songjaese@gmail.com", "제목", "내용");
       boolean a = emailSendService.sendMail(message , null , null);
        assertTrue(a);
    }

    @Test
    @DisplayName("템플릿 형태로 전송 테스트")
    void sendWithTpltest(){
        EmailEntiry message = new EmailEntiry("songjaese@gmail.com", "제목", "내용");
        Map<String, Object> tplData = new HashMap<>();
        tplData.put("duthNum", "12345678");
        boolean success = emailSendService.sendMail(message, "auth", tplData);
        assertTrue(success);
    }

    @Test
    @DisplayName("이메일 인증 번호 전송 테스트")
    void emailverifyTest(){
        boolean result = service.sendCode("songjaese@gmail.com");
        assertTrue(result);
    }


}
