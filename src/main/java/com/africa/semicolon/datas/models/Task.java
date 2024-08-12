package com.africa.semicolon.datas.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Setter
@Getter
@Document
public class Task {

    private String id;
    private String title;
    private String description;
    private boolean isImportant;
    private boolean isCompleted;
    private String dueDate;
    private LocalDate createdDate = LocalDate.now();

}
