package xyz.vinllage.global.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.Optional;

/*
 * JPA Auditing에서 현재 사용자 정보를 제공하는 구현체
 * - @EnableJpaAuditing과 함께 사용됨
 * - 엔티티의 @CreatedBy, @LastModifiedBy 값에 이 정보가 자동 반영됨
 */
@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        // 엔티티 저장, 수정 시 현재 로그인한 사용자의 이메일 정보 반환
        return Optional.ofNullable(memberUtil.isLogin() ? memberUtil.getMember().getEmail() : null);
    }
}
