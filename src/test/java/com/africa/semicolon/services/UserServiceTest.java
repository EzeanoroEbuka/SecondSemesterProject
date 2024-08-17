package com.africa.semicolon.services;

import com.africa.semicolon.dtos.requests.*;
import com.africa.semicolon.dtos.responses.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService.deleteAllUsers();
    }

    @Test
    public void testThatNoUserHasYetBeenSignedIn() {
        ReviewAllUsersResponse users = userService.reviewAll();
        assertTrue(users.getUsers().isEmpty());
    }
    @Test
    public void testThatUserIsSignedIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertEquals(request.getFirstName(),response.getFirstName());
        assertEquals(request.getLastName(),response.getLastName());
        assertEquals(1,userService.reviewAll().getUsers().size());
    }

    @Test
    public void testThatUserLogOut() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertTrue(response.isLogIn());
        assertEquals(request.getFirstName(),response.getFirstName());
        LogoutResponse response2 = userService.logout("adam.smith@gmail.com");
        assertFalse(response2.isLogIn());

    }

    @Test
    public void testThatUserCanLogIn() {
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertEquals(1,userService.reviewAll().getUsers().size());
        assertTrue(response.isLogIn());
        assertEquals(request.getFirstName(),response.getFirstName());
        LogoutResponse response2 = userService.logout("adam.smith@gmail.com");
        assertFalse(response2.isLogIn());
        LoginRequest request1 = new LoginRequest();
        request1.setEmail(request.getEmail());
        request1.setPassword(request.getPassword());
        LoginResponse response1 = userService.login(request1);
        assertTrue(response1.isLogIn());
    }

    @Test
    public void testForFindingUserByEmail(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertEquals(1,userService.reviewAll().getUsers().size());
        assertTrue(response.isLogIn());
        SignUpRequest request2 = new SignUpRequest();
        request2.setFirstName("Edith");
        request2.setLastName("Samuel");
        request2.setEmail("edi.sam@gmail.com");
        request2.setPassword("pass2");
        SignUpResponse response2 = userService.signUp(request2);
        assertEquals(2,userService.reviewAll().getUsers().size());
        assertTrue(response2.isLogIn());
        FindUserResponse newResponse = userService.findUser(request.getEmail());
        assertEquals(newResponse.getFirstName(),request.getFirstName());
        assertEquals(newResponse.getEmail(),request.getEmail());
    }

    @Test
    public void testThatUserCanBeDeletedByEmail(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Adam");
        request.setLastName("Smith");
        request.setEmail("adam.smith@gmail.com");
        request.setPassword("password");
        SignUpResponse response = userService.signUp(request);
        assertEquals(1,userService.reviewAll().getUsers().size());
        assertTrue(response.isLogIn());
        SignUpRequest request2 = new SignUpRequest();
        request2.setFirstName("Edith");
        request2.setLastName("Samuel");
        request2.setEmail("edi.samuel@gmail.com");
        request2.setPassword("pass2");
        SignUpResponse response2 = userService.signUp(request2);
        assertEquals(2,userService.reviewAll().getUsers().size());
        assertTrue(response2.isLogIn());
        userService.deleteUser(request.getEmail());
        assertEquals(1,userService.reviewAll().getUsers().size());
    }

    @Test
    public void testThatUserCanResetTheirPassword(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Edith");
        request.setLastName("Samuel");
        request.setEmail("edi.samuel@gmail.com");
        request.setPassword("pass2");
        SignUpResponse response = userService.signUp(request);
        assertEquals(1,userService.reviewAll().getUsers().size());
        assertTrue(response.isLogIn());
        ResetPasswordRequest request2 = new ResetPasswordRequest();
        request2.setNewPassword("password2");
        request2.setOldPassword(request.getPassword());
        request2.setEmail(request.getEmail());
        ResetPasswordResponse newResponse = userService.resetPassword(request2);
        assertEquals(newResponse.getMessage(),"You Have Successfully changed your Login Password");
    }

    @Test
    public void testThatUserCanCreateNewTask(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Edith");
        request.setLastName("Samuel");
        request.setEmail("edi.samuel@gmail.com");
        request.setPassword("pass2");
        userService.signUp(request);
        CreateTaskRequest taskRequest = new CreateTaskRequest();
        taskRequest.setTitle("OOP");
        taskRequest.setDescription("OOP means Object Oriented programming");
        taskRequest.setDueDate("7/4/20223");
        taskRequest.setImportant(true);
        taskRequest.setUserEmail("edi.samuel@gmail.com");
        userService.createTask(taskRequest);
        assertEquals(1,userService.reviewAll().getUsers().size());
        assertFalse(userService.findUser("edi.samuel@gmail.com").getTasks().isEmpty());
    }

    @Test
    public void testThatUserCanDeleteTask(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Edith");
        request.setLastName("Samuel");
        request.setEmail("edi.samuel@gmail.com");
        request.setPassword("pass2");
        userService.signUp(request);
        CreateTaskRequest taskRequest = new CreateTaskRequest();
        taskRequest.setTitle("OOP");
        taskRequest.setDescription("OOP means Object Oriented programming");
        taskRequest.setDueDate("7/4/20223");
        taskRequest.setImportant(true);
        taskRequest.setUserEmail("edi.samuel@gmail.com");
        userService.createTask(taskRequest);
        assertFalse(userService.findUser("edi.samuel@gmail.com").getTasks().isEmpty());
        DeleteTaskRequest deleteTaskRequest = new DeleteTaskRequest();
        deleteTaskRequest.setTitle("OOP");
        deleteTaskRequest.setUserEmail("edi.samuel@gmail.com");
        userService.deleteTask(deleteTaskRequest);
        assertTrue(userService.findUser("edi.samuel@gmail.com").getTasks().isEmpty());
    }

    @Test
    public void  testThatUserCanUpdateTheirTask(){
        SignUpRequest request = new SignUpRequest();
        request.setFirstName("Edith");
        request.setLastName("Samuel");
        request.setEmail("edi.samuel@gmail.com");
        request.setPassword("pass2");
        userService.signUp(request);
        CreateTaskRequest taskRequest = new CreateTaskRequest();
        taskRequest.setTitle("OOP");
        taskRequest.setDescription("OOP means Object Oriented programming");
        taskRequest.setDueDate("7/4/20223");
        taskRequest.setImportant(true);
        taskRequest.setUserEmail("edi.samuel@gmail.com");
        CreateTaskResponse newResponse = userService.createTask(taskRequest);

    }
}
