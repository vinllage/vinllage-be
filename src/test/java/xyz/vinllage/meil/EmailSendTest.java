package xyz.vinllage.meil;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.vinllage.global.email.entities.EmailMessage;
import xyz.vinllage.global.email.service.EmailSendService;

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
}
