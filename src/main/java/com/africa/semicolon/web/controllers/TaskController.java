package com.africa.semicolon.web.controllers;

import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.UpdateRequest;
import com.africa.semicolon.dtos.responses.*;
import com.africa.semicolon.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@RequestBody CreateTaskRequest request){
        try{
           CreateTaskResponse response = taskService.createNewTask(request);
           return  new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        catch(Exception exception){
            return  new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/edit/{title}")
    public ResponseEntity<?> updateTask(@RequestBody UpdateRequest request , @PathVariable("title") String title){
        try {
            UpdateResponse response = taskService.updateTask(request,title);
            return ResponseEntity.ok(response);
        }
        catch(Exception exception){
            return  new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<?> deleteTask(@PathVariable("title") String title){
        try{
            DeleteTaskResponse response = taskService.deleteTask(title);
            return ResponseEntity.ok(response);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> retrieveAllTasks(){
        try{
            ReviewAllTaskResponse response = taskService.reviewAllTask();
            return ResponseEntity.ok(response);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<?> retrieveTask(@PathVariable("title") String title){
        try{
            TaskReviewResponse response = taskService.reviewTask(title);
            return ResponseEntity.ok(response);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
