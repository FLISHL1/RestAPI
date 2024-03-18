package com.restapi.controller;

import com.restapi.dto.Album;
import com.restapi.dto.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements ApiController<User>{
    private final RestTemplate restTemplate;
    private final String DEFAULT_URL = "http://jsonplaceholder.typicode.com/users";
    @Override
    @GetMapping()
    public ResponseEntity<User[]> getAll(@RequestParam(required = false) Map<String, String> args) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a, b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + subUrl.toString(), User[].class)
                , HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Validated User user) {
        return new ResponseEntity<>(restTemplate.postForObject(DEFAULT_URL, user, User.class)
                , HttpStatus.OK);
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<User> getEntity(@PathVariable Long userId) {
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + userId, User.class)
                , HttpStatus.OK);
    }

    @Override
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateEntity(@RequestBody @Validated User user, @PathVariable Long userId) {
        var responsePost = restTemplate.exchange(DEFAULT_URL + "/" + userId,
                HttpMethod.PUT,
                new HttpEntity<User>(user),
                User.class);
        return new ResponseEntity<>(responsePost.getBody(), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{userId}")
    public ResponseEntity<User> patchEntity(@RequestBody @Validated Map<String, ?> args, @PathVariable Long userId) {
        return new ResponseEntity<>(restTemplate.patchForObject(DEFAULT_URL + "/" + userId, args, User.class)
                , HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long userId) {
        restTemplate.delete(DEFAULT_URL + "/" + userId);
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }

    @GetMapping("/{userId}/albums")
    public ResponseEntity<String> getUserAlbums(@RequestParam(required = false) Map<String, String> args, @PathVariable Long userId) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + userId + "/albums" + subUrl, String.class)
                , HttpStatus.OK);
    }
    @GetMapping("/{userId}/todos")
    public ResponseEntity<String> getUserToDos(@RequestParam(required = false) Map<String, String> args, @PathVariable Long userId) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + userId + "/todos" + subUrl, String.class)
                , HttpStatus.OK);
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<String> getUserPosts(@RequestParam(required = false) Map<String, String> args, @PathVariable Long userId) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + userId + "/posts" + subUrl, String.class)
                , HttpStatus.OK);
    }
}
