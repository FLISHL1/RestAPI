package com.restapi.service;

import com.restapi.dto.LoginRequest;
import com.restapi.dto.LoginResponse;
import com.restapi.entity.User;
import com.restapi.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(LoginRequest loginData){
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        var role = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();

        var token = jwtIssuer.issue(1L, user.getUsername(), role);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    public String attemptSignUp(LoginRequest data){
        if (userService.readUserByUsername(data.getUsername()) != null){

            return "User " + data.getUsername() + " is exist!";
        }
        userService.save(data);
        return "User " + data.getUsername() + " successfully register";
    }

}
