package xyz.vinllage.member.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import xyz.vinllage.member.controllers.RequestLoginToken;
import xyz.vinllage.member.controllers.RequestToken;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.repositories.MemberRepository;

@Component
@RequiredArgsConstructor
public class TokenValidator implements Validator {

    private final MemberRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public boolean supports(Class<?> clazz) {

        return clazz.isAssignableFrom(RequestToken.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        if (!(target instanceof RequestLoginToken))return;

        RequestToken form = (RequestToken) target;
        Member member = repository.findByEmail(form.getEmail()).orElse(null);

        if (member == null) {
            errors.reject("NotFound.member.or.password");
        }

        // 비밀번호 검증

        if (member != null && !encoder.matches(form.getPassword(), member.getPassword())) {
            errors.reject("NotFound.member.or.password");

        }
    }
}
