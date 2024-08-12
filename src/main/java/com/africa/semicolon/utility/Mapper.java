package com.africa.semicolon.utility;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.UpdateRequest;
import com.africa.semicolon.dtos.responses.CreateTaskResponse;
import com.africa.semicolon.dtos.responses.ReminderResponse;
import com.africa.semicolon.dtos.responses.UpdateResponse;

public class Mapper {

    public static Task mapping(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setImportant(request.isImportant());
        task.setDueDate(request.getDueDate());
        return task;
    }
    public static CreateTaskResponse responseMapping(Task task) {
        CreateTaskResponse response = new CreateTaskResponse();
        response.setTitle(task.getTitle());
        response.setCompleted(task.isCompleted());
        response.setImportant(task.isImportant());
        response.setDueDate(task.getDueDate());
        response.setMessage("Task created successfully");
        return response;
    }

    public static ReminderResponse remindMapping(Task task) {
        ReminderResponse remind = new ReminderResponse();
        remind.setTitle(task.getTitle());
        remind.setCompleted(task.isCompleted());
        remind.setDueDate(task.getDueDate());
        remind.setImportant(task.isImportant());
        remind.setMessage("Reminder!!!.. Task is not yet completed");
        return remind;
    }

    public static Task updateMapping(UpdateRequest  request, Task task) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setImportant(request.isImportant());
        task.setDueDate(request.getDueDate());
        task.setCompleted(request.isComplete());
        return task;
    }

    public static UpdateResponse updateResponseMapping(Task task) {
        UpdateResponse  response = new UpdateResponse();
        response.setTitle(task.getTitle());
        response.setCompleted(task.isCompleted());
        response.setImportant(task.isImportant());
        response.setDueDate(task.getDueDate());   ;
        response.setMessage("Task Updated Successfully");
        return response;
    }
}
