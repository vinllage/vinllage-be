package xyz.vinllage.global.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageSourceConfig {
    public MessageSource messageSource() {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
        ms.addBasenames("messages.commons", "messages.validations", "messages.errors");
        ms.addBasenames("UTF-8");
        ms.setUseCodeAsDefaultMessage(true);

        return ms;
    }
}
