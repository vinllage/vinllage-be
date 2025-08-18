package xyz.vinllage.member.libs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import xyz.vinllage.member.MemberInfo;
import xyz.vinllage.member.constants.Authority;
import xyz.vinllage.member.entities.Member;

import java.util.Objects;
import java.util.UUID;

/*
*
* 로그인 상태와 회원 정보를 편리하게 확인
*
*/
@Component
@RequiredArgsConstructor
public class MemberUtil {
    private final HttpServletRequest request;

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

    /**
     * 회원 구분 해시
     *  비회원 : 요청헤더 - User-Hash가 있으면 그걸로 대체
     *  회원 : 회원 번호
     * @return
     */
    public int getUserHash() {
        String userHash = request.getHeader("User-Hash");
        userHash = StringUtils.hasText(userHash) ? userHash : UUID.randomUUID().toString();

        return isLogin() ? Objects.hash(getMember().getSeq()) : Objects.hash(userHash);
    }
}
