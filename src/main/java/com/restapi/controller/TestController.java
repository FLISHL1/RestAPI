package com.restapi.controller;

import com.restapi.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String test(){
        return "Hello!";
    }

    @GetMapping("/security")
    public String security(@AuthenticationPrincipal User principal){
        return "Oh yea as user " + principal.getUsername()
                + " " + principal.getRoles();
    }
    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal User principal){
        return "You are ADMIN: UserID" + principal.getId();
    }
}
