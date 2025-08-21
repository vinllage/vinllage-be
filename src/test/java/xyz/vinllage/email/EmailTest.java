package xyz.vinllage.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.vinllage.global.email.entities.EmailEntiry;
import xyz.vinllage.global.email.sevices.EmailSendService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailSendService service;

    @Test
    void test(){
        EmailEntiry emailEntiry = new EmailEntiry("jigu1993@naver.com", "제목", "내용" );
        String tpl = "email";
        Map<String , Object> tplDate = new HashMap<>();
        tplDate.put("authNum", "12348");

        boolean c = service.sendMail(emailEntiry, tpl, tplDate);

        assertTrue(c);
    }



}
