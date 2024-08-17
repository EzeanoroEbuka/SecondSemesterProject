package com.africa.semicolon.services;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.datas.models.User;
import com.africa.semicolon.datas.repositories.UserRepository;
import com.africa.semicolon.dtos.requests.*;
import com.africa.semicolon.dtos.responses.*;
import com.africa.semicolon.utility.MapUsers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final TaskService taskService;
    private final UserRepository userRepository;

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
    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
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

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest request) {
        findUser(request.getUserEmail());
        User user = findUserByEmail(request.getUserEmail());
        CreateTaskResponse response = taskService.createNewTask(request);
        Task task = MapUsers.createTaskMapping(response);
        user.getTasks().add(task);
        userRepository.save(user);
        return response;
    }

    @Override
    public UpdateResponse updateTask(UpdateRequest request) {
        return null;
    }

    @Override
    public TaskReviewResponse reviewTask(GetTaskRequest request) {
        return null;
    }

    @Override
    public ReviewAllTaskResponse reviewAllTask() {
        return null;
    }

    @Override
    public DeleteAllTaskResponse clearAllTask() {
        return null;
    }

    @Override
    public DeleteTaskResponse deleteTask(DeleteTaskRequest request) {
        DeleteTaskResponse response = new DeleteTaskResponse();
        User foundUser = findUserByEmail(request.getUserEmail());
        for (Task task : foundUser.getTasks()) {
            if(task.getTitle().equalsIgnoreCase(request.getTitle())) {
                response = taskService.deleteTask(request.getTitle());
                foundUser.getTasks().remove(task);
                userRepository.save(foundUser);
                return response;
            }
        }
        throw  new RuntimeException("Task not found...please try again");
    }

    private User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw  new RuntimeException("User not found");
        }
        return user;
    }
}
