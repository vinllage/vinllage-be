package xyz.vinllage.member.controllers;

import lombok.Data;
import xyz.vinllage.member.constants.SocialChannel;

/**
 * 사용자가 토큰을 요청할 때 전달하는 데이터 형식을 정의하는 DTO(Data Transfer Object)
 */
@Data
public class RequestToken {
    private boolean social;
    private String email;
    private String password;
    private SocialChannel socialChannel;
    private String socialToken;


}
