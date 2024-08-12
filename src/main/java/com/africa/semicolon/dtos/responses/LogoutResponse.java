package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogoutResponse {
    private boolean logIn;
    private String message;
}
