package xyz.vinllage.mypage.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import xyz.vinllage.global.validators.MobileValidator;
import xyz.vinllage.global.validators.PasswordValidator;
import xyz.vinllage.mypage.controllers.RequestProfile;

@Lazy
@Component
@RequiredArgsConstructor
public class ProfileValidator implements Validator, PasswordValidator, MobileValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(RequestProfile.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        RequestProfile form = (RequestProfile) target;
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();

        if (StringUtils.hasText(password)) {
            if (!StringUtils.hasText(confirmPassword)) {
                errors.rejectValue("confirmPassword", "NotBlank");
            }

            if (password.length() < 8) {
                errors.rejectValue("password", "Size");
            }

            if (!checkAlpha(password, false) || !checkNumber(password) || !checkSpecialChars(password)) {
                errors.rejectValue("password", "Complexity");
            }

            if (StringUtils.hasText(confirmPassword) && !password.equals(confirmPassword)) {
                errors.rejectValue("confirmPassword", "Mismatch");
            }
        }

        String mobile = form.getMobile();

        if (!checkMobile(mobile)) {
            errors.rejectValue("mobile", "Format");
        }

    }
}
