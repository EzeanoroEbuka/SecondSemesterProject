package com.africa.semicolon.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateTaskResponse {

    private String id;
    private String title;
    private String description;
    private boolean isCompleted;
    private boolean isImportant;
    private String dueDate;
    private String message;
    private LocalDate createDate;

}