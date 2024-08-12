package com.africa.semicolon.services;

import com.africa.semicolon.dtos.requests.LoginRequest;
import com.africa.semicolon.dtos.requests.ResetPasswordRequest;
import com.africa.semicolon.dtos.requests.SignUpRequest;
import com.africa.semicolon.dtos.responses.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    SignUpResponse signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
    ResetPasswordResponse reset(ResetPasswordRequest request);
    LogoutResponse logout(String email);
    ReviewAllUsersResponse reviewAll();
    FindUserResponse findUser(String email);
    DeleteUserResponse deleteUser(String email);
    DeleteAllUser deleteAllUsers();
}
