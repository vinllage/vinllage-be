package xyz.vinllage.global.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/*
*
* 메세지 프로퍼티 파일을 읽어서 메시지 키에 따라 텍스트 제공
*
*/
public class MessageSourceConfig {
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.addBasenames("messages.commons", "messages.validations", "messages.errors");
        ms.setDefaultEncoding("UTF-8");
        ms.setUseCodeAsDefaultMessage(true); // 메시지 키가 없으면 키 자체를 기본 메시지로 사용

        return ms;
    }
}
