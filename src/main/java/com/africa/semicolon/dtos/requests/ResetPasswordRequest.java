package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ResetPasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}

