package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String password;
    private String email;

}
