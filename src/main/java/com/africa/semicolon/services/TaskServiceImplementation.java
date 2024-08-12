package com.africa.semicolon.services;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.datas.repositories.TaskRepository;
import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.UpdateRequest;
import com.africa.semicolon.dtos.responses.*;
import com.africa.semicolon.utility.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImplementation implements TaskService {

    private TaskRepository taskRepository;

    @Override
    public CreateTaskResponse createNewTask(CreateTaskRequest request) {
        CreateTaskResponse response;
        Task task = Mapper.mapping(request);
         Task newTask = taskRepository.save(task);
        response = Mapper.responseMapping(newTask);
        return response;
    }

    @Override
    public ReminderResponse taskReminder(String title) {
        Task foundTask = taskRepository.findByTitleIgnoreCase(title);
        if(foundTask != null) {
            return Mapper.remindMapping(foundTask);
        }
        throw new IllegalArgumentException("Task not found");
    }

    @Override
    public UpdateResponse updateTask(UpdateRequest request, String title) {
        Task foundTask = taskRepository.findByTitleIgnoreCase(title);
        if(foundTask != null) {
            Task updatedTask = Mapper.updateMapping(request,foundTask);
            taskRepository.save(updatedTask);
            return  Mapper.updateResponseMapping(updatedTask);
        }
        throw new IllegalArgumentException("Task not found");
    }

    @Override
    public TaskReviewResponse reviewTask(String title) {
        TaskReviewResponse review = new TaskReviewResponse();
        Task foundTask = taskRepository.findByTitleIgnoreCase(title);
        if(foundTask != null) {
            review.setId(foundTask.getId());
            review.setTitle(foundTask.getTitle());
            review.setDescription(foundTask.getDescription());
            review.setDueDate(foundTask.getDueDate());
            review.setCompleted(foundTask.isCompleted());
            review.setImportant(foundTask.isImportant());
            return  review;
        }
        throw new IllegalArgumentException("Task not found");
    }

    @Override
    public ReviewAllTaskResponse reviewAllTask() {
        ReviewAllTaskResponse reviewAll = new ReviewAllTaskResponse();
        reviewAll.setTasks(taskRepository.findAll());
        reviewAll.setMessage("This Are All Your task");
        return  reviewAll;
    }

    @Override
    public DeleteAllTaskResponse clearAllTask() {
        DeleteAllTaskResponse deleteAll = new DeleteAllTaskResponse();
        taskRepository.deleteAll();
        deleteAll.setMessage("All tasks have been Deleted Successfully");
        return  deleteAll;
    }

    @Override
    public DeleteTaskResponse deleteTask(String title) {
        DeleteTaskResponse response = new DeleteTaskResponse();
        Task foundTask = taskRepository.findByTitleIgnoreCase(title);
        if(foundTask != null) {
            taskRepository.delete(foundTask);
            response.setTitle(title);
            response.setMessage(title + " " + "Task Has Been Deleted Successfully");
            return response;
        }
        throw new IllegalArgumentException("Task not found");
    }
}
