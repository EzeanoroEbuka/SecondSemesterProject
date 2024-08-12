package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTaskRequest {
    private String title;
    private String description;
    private boolean isImportant;
    private String dueDate;

}