package com.africa.semicolon.datas.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("userTodo")
@Setter
@Getter
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean logIn;

    @DBRef
    private List<Task> tasks = new ArrayList<>();

}
