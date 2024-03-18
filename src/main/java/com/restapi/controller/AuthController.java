package com.restapi.controller;

import com.restapi.dto.LoginResponse;
import com.restapi.dto.LoginRequest;
import com.restapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest req){
        return new ResponseEntity<>(authService.attemptLogin(req), HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Validated LoginRequest req){
        System.out.println(req);
        return new ResponseEntity<>(authService.attemptSignUp(req), HttpStatus.OK);
    }
}
