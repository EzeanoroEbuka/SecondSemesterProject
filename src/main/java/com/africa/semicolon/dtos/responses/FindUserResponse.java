package com.africa.semicolon.dtos.responses;

import com.africa.semicolon.datas.models.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Setter
@Getter
public class FindUserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @DBRef
    private List<Task> tasks;


}
