package com.restapi.controller;

import com.restapi.dto.UserDTO;
import com.restapi.entity.User;
import com.restapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    @Autowired

    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/signup")
    public ResponseEntity<String> registration(@RequestBody UserDTO dto){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if(userService.readUserByUsername(dto.getUsername()) != null){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.OK);
        }

        userService.save(dto);
        System.out.println(dto.getUsername());
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDTO dto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),
                dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> readAll(){
        System.out.println(Arrays.toString(new List[]{userService.readAll()}));
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

}
