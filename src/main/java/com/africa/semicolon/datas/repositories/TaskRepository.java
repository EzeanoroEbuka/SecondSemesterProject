package com.africa.semicolon.datas.repositories;

import com.africa.semicolon.datas.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    Task findByTitleIgnoreCase(String title);

}
