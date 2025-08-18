package xyz.vinllage.board.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestPassword {
    @NotBlank
    private String password;
}
