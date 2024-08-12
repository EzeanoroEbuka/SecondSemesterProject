package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskReviewResponse {
    private String id;
    private String title;
    private String description;
    private boolean isImportant;
    private boolean isCompleted;
    private String dueDate;
}
