package com.restapi.controller;

import com.restapi.dto.LoginResponse;
import com.restapi.dto.UserDTO;
import com.restapi.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtIssuer jwtIssuer;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated UserDTO dto){
        var token = jwtIssuer.issue(1L, dto.getUsername())
        return LoginResponse.builder()
                .accessToken("12434321432")
                .build();
    }
}
