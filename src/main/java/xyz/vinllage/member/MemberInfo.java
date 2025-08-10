package xyz.vinllage.member;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import xyz.vinllage.member.entities.Member;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


/*
*
* 로그인한 회원의 권한, 비밀번호, 계정 상태.. Spring Security에 전달
* 해당 정보 바탕으로 Spring Security 가 로그인과 권한 관리
*/
@Data
@Builder
public class MemberInfo implements UserDetails {

    private Member member; // 회원 정보 객체

    // 사용자 권한 정보 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member == null ? null : List.of(new SimpleGrantedAuthority(member.getAuthority().name()));
    }

    // 사용자 비밀번호 반환
    @Override
    public String getPassword() {
        return member == null ? null : member.getPassword();
    }

    // 사용자 이름(로그인 ID) 반환
    @Override
    public String getUsername() {
        return member == null ? null : member.getEmail();
    }

    // 계정 활성화 여부 반환
    @Override
    public boolean isEnabled() {
        return member != null && member.getDeletedAt() == null;
    }

    // 비밀번호 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return member != null && member.getCredentialChangedAt().isAfter(LocalDateTime.now().minusDays(30L));
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return member != null && member.getExpired() == null;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return member != null && !member.isLocked();
    }
}
