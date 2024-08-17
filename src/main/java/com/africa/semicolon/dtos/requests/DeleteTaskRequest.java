package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DeleteTaskRequest {
    private String title;
    private String userEmail;
}
