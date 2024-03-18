package com.restapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private Long id;
    private String title;
    private Long userId;

    public Album(String title, Long userId){
        this.title = title;
        this.userId = userId;
    }
    public String toString() {
        return String.format("\n id: %s \n title: %s \n userId: %s \n", id, title, userId);
    }
}
