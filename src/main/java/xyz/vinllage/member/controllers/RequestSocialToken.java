package xyz.vinllage.member.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import xyz.vinllage.member.constants.SocialChnannel;

@Data
public class RequestSocialToken extends RequestToken{
    @NotBlank
    private SocialChnannel chnannel;
    @NotBlank
    private String token;
}
