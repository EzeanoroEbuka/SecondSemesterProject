package com.africa.semicolon.dtos.responses;

import com.africa.semicolon.datas.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReviewAllTaskResponse {
    private List<Task> tasks;
    private String message;

}
