package xyz.vinllage.global.email.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestEmail {
    @NotBlank
    @Email
    String Email;
}
