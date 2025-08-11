package xyz.vinllage.member.libs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import xyz.vinllage.member.MemberInfo;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.entities.Member;

/*
*
* 로그인 상태와 회원 정보를 편리하게 확인
*
*/
@Component
public class MemberUtil {
    // 로그인 상태인지 확인, 로그인이면 true 아니면 false
    public boolean isLogin() {
        return getMember() != null;
    }

    // 현재 로그인한 사용자가 관리자 권한인지 확인
    public boolean isAdmin() {
        return isLogin() && getMember().getAuthority() == Authority.ADMIN;
    }

    // 현재 인증된 로그인 회원 정보를 가져옴
    public Member getMember() {
        // 스프링 시큐리티에서 현재 인증 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증 주체가 MemberInfo 타입이면 회원 정보 반환
        if (authentication != null && authentication.getPrincipal() instanceof MemberInfo memberInfo) {
            return memberInfo.getMember();
        }

        return null;
    }
}
