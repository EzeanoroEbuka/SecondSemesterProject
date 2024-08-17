package com.africa.semicolon.utility;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.datas.models.User;
import com.africa.semicolon.dtos.requests.CreateTaskRequest;
import com.africa.semicolon.dtos.requests.SignUpRequest;
import com.africa.semicolon.dtos.responses.CreateTaskResponse;
import com.africa.semicolon.dtos.responses.FindUserResponse;
import com.africa.semicolon.dtos.responses.SignUpResponse;

public class MapUsers {

    public static User signInMapping(SignUpRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLogIn(true);
        return user;
    }
    public static SignUpResponse signInResponseMapping(SignUpResponse response, User user) {
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setLogIn(true);
        response.setMessage(" Welcome " + user.getFirstName() + " Sign up successful");
        return response;
    }

    public static FindUserResponse findUserMapping(User foundUser) {
        FindUserResponse response = new FindUserResponse();
        response.setFirstName(foundUser.getFirstName());
        response.setLastName(foundUser.getLastName());
        response.setEmail(foundUser.getEmail());
        response.setId(foundUser.getId());
        response.setEmail(foundUser.getEmail());
        response.setTasks(foundUser.getTasks());
        return response;

    }
    public static Task createTaskMapping(CreateTaskResponse response) {
        Task task = new Task();
        task.setId(response.getId());
        task.setTitle(response.getTitle());
        task.setImportant(response.isImportant());
        task.setCompleted(response.isCompleted());
        task.setDueDate(response.getDueDate());
        task.setDescription(response.getDescription());
        task.setCreatedDate(response.getCreateDate());
        return task;
    }

    public static User mapUser(FindUserResponse foundUser) {
        User user  = new User();
        user.setId(foundUser.getId());
        user.setFirstName(foundUser.getFirstName());
        user.setLastName(foundUser.getLastName());
        user.setEmail(foundUser.getEmail());

        return user;
    }

}
