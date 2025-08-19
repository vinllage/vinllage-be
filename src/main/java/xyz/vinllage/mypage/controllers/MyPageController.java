package xyz.vinllage.mypage.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="마이페이지 API", description = "회원 정보 수정, 탈퇴, 분리수거 결과 리스트 및 누적 통계를 확인")
public class MyPageController {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;
    private final PasswordEncoder encoder;

    @GetMapping
    @PreAuthorize("isAuthenticated()") // 로그인 필수
    public Member myInfo() {
        return memberUtil.getMember(); // 현재 로그인한 사용자 엔티티 조회
    }

    @PutMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public Member updateProfile(@Valid @RequestBody RequestProfile form) {
        Member me = memberUtil.getMember();

        // 비밀번호 변경 (선택)
        if (StringUtils.hasText(form.getPassword())) {
            me.setPassword(encoder.encode(form.getPassword()));
            me.setCredentialChangedAt(LocalDateTime.now());
        }
        // 이름/전화번호 변경
        me.setName(form.getName());
        me.setMobile(form.getMobile().replaceAll("\\D", ""));

        return memberRepository.saveAndFlush(me);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMe() {
        // 계정 삭제
        Member me = memberUtil.getMember();
        memberRepository.delete(me);
    }
}
