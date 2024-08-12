package com.africa.semicolon.services;

import com.africa.semicolon.datas.models.User;
import com.africa.semicolon.datas.repositories.UserRepository;
import com.africa.semicolon.dtos.requests.LoginRequest;
import com.africa.semicolon.dtos.requests.ResetPasswordRequest;
import com.africa.semicolon.dtos.requests.SignUpRequest;
import com.africa.semicolon.dtos.responses.*;
import com.africa.semicolon.utility.MapUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        SignUpResponse response = new SignUpResponse();
        User existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser == null) {
            User newUser = MapUsers.signInMapping(request);
            userRepository.save(newUser);
           return MapUsers.signInResponseMapping(response,newUser);
        }
        throw  new RuntimeException("User already exists");
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw  new RuntimeException("User not found");
        }
        if (user.getPassword().equals(request.getPassword())) {
            user.setLogIn(true);
            userRepository.save(user);
            response.setLogIn(user.isLogIn());
            response.setMessage("Login successful");
            return response;
        }
        throw  new RuntimeException("Wrong password");
    }

    @Override
    public ResetPasswordResponse reset(ResetPasswordRequest request) {
        ResetPasswordResponse response = new ResetPasswordResponse();
        User foundUser = findUserByEmail(request.getEmail());
        if (foundUser.getPassword().equals(request.getOldPassword())) {
            foundUser.setPassword(request.getNewPassword());
            userRepository.save(foundUser);
            response.setMessage("You Have Successfully changed your Login Password");
            return response;
        }
        throw  new RuntimeException("Wrong password");
    }

    @Override
    public LogoutResponse logout(String email) {
        LogoutResponse response = new LogoutResponse();
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setLogIn(false);
            userRepository.save(user);
            response.setMessage("Successfully logged out");
            response.setLogIn(user.isLogIn());
            return response;
        }
        throw  new RuntimeException("User not Logged in");
    }

    @Override
    public ReviewAllUsersResponse reviewAll() {
        ReviewAllUsersResponse response = new ReviewAllUsersResponse();
        List<User> users = userRepository.findAll();
        response.setUsers(users);
        response.setMessage("This Are All The Users");
        return response;
    }

    @Override
    public FindUserResponse findUser(String email) {
        User user = findUserByEmail(email);
        return MapUsers.findUserMapping(user);

    }

    @Override
    public DeleteUserResponse deleteUser(String email) {
        User user = findUserByEmail(email);
        userRepository.delete(user);
        DeleteUserResponse deleteResponse = new DeleteUserResponse();
        deleteResponse.setFirstName(user.getFirstName());
        deleteResponse.setMessage("User deleted successfully");
        return deleteResponse;
    }

    @Override
    public DeleteAllUser deleteAllUsers() {
        DeleteAllUser deleteAllUser = new DeleteAllUser();
        userRepository.deleteAll();
        deleteAllUser.setMassage("All Users have been Deleted Successfully");
        return  deleteAllUser;
    }

    private User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw  new RuntimeException("User not found");
        }
        return user;
    }
}
