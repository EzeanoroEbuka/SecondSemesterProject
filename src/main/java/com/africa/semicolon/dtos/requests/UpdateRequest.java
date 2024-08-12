package com.africa.semicolon.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRequest {
    private String title;
    private String description;
    private boolean isImportant;
    private boolean isComplete;
    private String dueDate;

}
