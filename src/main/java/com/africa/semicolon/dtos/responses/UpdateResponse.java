package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponse {
    private String title;
    private boolean isCompleted;
    private boolean isImportant;
    private String message;
    private String dueDate;
}
