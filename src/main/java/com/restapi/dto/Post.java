package com.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long id;
    private String title;
    private String body;
    private Long userId;

    public Post(String title, String body, Long userId){
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
    public String toString() {
        return String.format("\n id: %s \n title: %s \n body: %s \n userId: %s \n", id, title, body, userId);
    }
}