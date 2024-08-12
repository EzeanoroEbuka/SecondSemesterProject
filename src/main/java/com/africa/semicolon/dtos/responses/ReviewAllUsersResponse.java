package com.africa.semicolon.dtos.responses;

import com.africa.semicolon.datas.models.Task;
import com.africa.semicolon.datas.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReviewAllUsersResponse {
    private List<User> users;
    private String message;


}
