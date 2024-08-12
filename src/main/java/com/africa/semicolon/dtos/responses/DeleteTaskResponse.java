package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteTaskResponse {
    private String title;
    private String message;
}
