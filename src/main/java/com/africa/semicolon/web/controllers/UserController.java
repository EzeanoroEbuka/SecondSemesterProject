package com.africa.semicolon.web.controllers;

import com.africa.semicolon.dtos.requests.LoginRequest;
import com.africa.semicolon.dtos.requests.SignUpRequest;
import com.africa.semicolon.dtos.responses.LoginResponse;
import com.africa.semicolon.dtos.responses.LogoutResponse;
import com.africa.semicolon.dtos.responses.ReviewAllUsersResponse;
import com.africa.semicolon.dtos.responses.SignUpResponse;
import com.africa.semicolon.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest request) {
        try {
            SignUpResponse signup = userService.signUp(request);
             return new ResponseEntity<>(signup, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/logout/{email}")
    public LogoutResponse logOut(@PathVariable("email") String email) {
        return userService.logout(email);

    }
    @PatchMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping("/findAllUser")
    public ReviewAllUsersResponse getAllUsers() {
        return userService.reviewAll();
    }

}
