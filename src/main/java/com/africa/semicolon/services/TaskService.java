package com.africa.semicolon.services;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.UpdateRequest;
import com.africa.semicolon.dtos.responses.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    CreateTaskResponse createNewTask(CreateTaskRequest request);
    ReminderResponse taskReminder(String title);
    UpdateResponse updateTask(UpdateRequest request , String title);
    TaskReviewResponse reviewTask(String title);
    ReviewAllTaskResponse reviewAllTask();
    DeleteAllTaskResponse clearAllTask();
    DeleteTaskResponse deleteTask(String title);
}
