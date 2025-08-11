package xyz.vinllage.member.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 사용자가 토큰을 요청할 때 전달하는 데이터 형식을 정의하는 DTO(Data Transfer Object)
 */
@Data
public class RequestToken {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
