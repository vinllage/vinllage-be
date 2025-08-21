package xyz.vinllage.member.controllers;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import xyz.vinllage.member.constants.SocialChannel;

/**
 * 내가 이 정보로 회원가입 하겠다
 */
@Data
public class RequestJoin {

    private String gid;

    @NotBlank @Email
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    @NotBlank
    private String confirmPassword;

    private SocialChannel socialChannel;
    private String socialToken;

    @NotBlank
    private String name;

    @NotBlank
    private String mobile;

    @AssertTrue
    private boolean termsAgree;
}
