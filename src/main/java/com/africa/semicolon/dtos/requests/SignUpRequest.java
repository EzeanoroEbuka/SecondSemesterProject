package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
