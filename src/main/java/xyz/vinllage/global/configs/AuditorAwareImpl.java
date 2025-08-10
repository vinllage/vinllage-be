package xyz.vinllage.global.configs;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.MemberUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import xyz.vinllage.member.libs.MemberUtil;

import java.util.Optional;

/*
* 엔티티 저장, 수정 시 현재 로그인한 사용자의 이메일 정보 반환
* (작성자나 수정자 정보를 자동으로 채워줌)
*/

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    private final MemberUtil memberUtil;

    @Override
    public Optional<String> getCurrentAuditor() {
        // 로그인 상태이면 이메일 반환, 아니면 빈 값
        return Optional.ofNullable(memberUtil.isLogin() ? memberUtil.getMember().getEmail() : null);
    }
}
