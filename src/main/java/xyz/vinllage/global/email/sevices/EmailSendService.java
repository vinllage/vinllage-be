package xyz.vinllage.global.email.sevices;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String code, String template) {
        // 1) 템플릿에 전달할 데이터
        Context context = new Context();
        context.setVariable("code", code);

        // 2) 템플릿 처리
        String htmlContent = templateEngine.process(template, context);

        try {
            // 3) 메일 작성
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML

            // 4) 메일 발송
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendTemporaryPassword (String to , String code ){
        sendEmail(to, "임시 비밀 번호 발급 ",code , "");
    }

    public void sendVerificationEmail(String to, String code) {
        sendEmail(to, "이메일 인증 코드", code, "email/email");
    }

    public void sendTemporaryPasswordEmail(String to, String code) {
        sendEmail(to, "임시 비밀번호 발급", code, "member/email");
    }

}
