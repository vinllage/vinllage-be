package xyz.vinllage.mypage.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.vinllage.member.entities.Member;
import xyz.vinllage.member.libs.MemberUtil;
import xyz.vinllage.member.repositories.MemberRepository;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mypage")
@Tag(name = "마이페이지 API", description = "회원 정보 조회/수정/탈퇴")
public class MyPageController {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;
    private final PasswordEncoder encoder;

    /**
     * 1) 내 정보 조회
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "내 정보 조회")
    public Member myInfo() {
        return memberUtil.getMember();
    }

    /**
     * 2) 프로필 수정 (비밀번호는 선택)
     *
     * @param form
     * @return
     */
    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "프로필 수정 (이름/휴대폰, 비밀번호 선택)")
    public Member updateProfile(@Valid @RequestBody RequestProfile form) {
        Member me = memberUtil.getMember();

        // 비밀번호(선택)
        if (StringUtils.hasText(form.getPassword())) {
            me.setPassword(encoder.encode(form.getPassword().trim()));
            me.setCredentialChangedAt(LocalDateTime.now());
        }

        // 이름/휴대폰
        me.setName(form.getName().trim());
        me.setMobile(form.getMobile().replaceAll("\\D", ""));

        if (form.getProfileImage() != null) {
            me.setProfileImage(form.getProfileImage());
        }

        return memberRepository.saveAndFlush(me);
    }

    /**
     * 3) 회원 탈퇴 (소프트 삭제 + Spring Security 로그아웃)
     */
    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원 탈퇴 (소프트 삭제, 로그아웃 처리)")
    public void deleteMe(HttpServletRequest request) {
        Member me = memberUtil.getMember();

        // 소프트 삭제 처리
        if (!me.isDeleted()) {
            me.setDeletedAt(LocalDateTime.now());
            memberRepository.saveAndFlush(me); // DB 업데이트만 수행
        }

        // Spring Security 인증 초기화
        org.springframework.security.core.context.SecurityContextHolder.clearContext();

        // HTTP 세션 무효화: 브라우저와 연결된 세션 제거
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
    }
}
