package com.africa.semicolon.services;

import com.africa.semicolon.dtos.requests.*;
import com.africa.semicolon.dtos.responses.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
    ResetPasswordResponse resetPassword(ResetPasswordRequest request);
    LogoutResponse logout(String email);
    ReviewAllUsersResponse reviewAll();
    FindUserResponse findUser(String email);
    DeleteUserResponse deleteUser(String email);
    DeleteAllUser deleteAllUsers();
    CreateTaskResponse createTask (CreateTaskRequest request);
    UpdateResponse updateTask(UpdateRequest request);
    TaskReviewResponse reviewTask(GetTaskRequest request);
    ReviewAllTaskResponse reviewAllTask();
    DeleteAllTaskResponse clearAllTask();
    DeleteTaskResponse deleteTask(DeleteTaskRequest request);

}
