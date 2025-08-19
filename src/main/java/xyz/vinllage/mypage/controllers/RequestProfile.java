package xyz.vinllage.mypage.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestProfile {

    private String password;

    private String confirmPassword;

    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String mobile;


}
