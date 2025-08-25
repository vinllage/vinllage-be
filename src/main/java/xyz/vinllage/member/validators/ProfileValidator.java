package xyz.vinllage.member.validators;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import xyz.vinllage.global.validators.MobileValidator;
import xyz.vinllage.global.validators.PasswordValidator;
import xyz.vinllage.member.controllers.RequestProfile;

@Component
public class ProfileValidator implements Validator, PasswordValidator, MobileValidator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestProfile.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /**
         *
         * password, confirmPassword는
         * password 값이 있는 경우
         *  - confirmPassword 필수 항목
         *  - 비밀번호 자리수, 복잡성 체크
         *  - password, confirmPassword 일치 여부 체크
         *
         */

        RequestProfile form = (RequestProfile) target;
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        if (StringUtils.hasText(password)) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank");
        }
    }
}
