package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponse {
    private String firstName;
    private String lastName;
    private String message;
    private boolean logIn;
}
