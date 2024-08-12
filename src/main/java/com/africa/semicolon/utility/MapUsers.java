package com.africa.semicolon.utility;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.datas.models.User;
import com.africa.semicolon.dtos.requests.SignUpRequest;
import com.africa.semicolon.dtos.responses.FindUserResponse;
import com.africa.semicolon.dtos.responses.SignUpResponse;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

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

}
