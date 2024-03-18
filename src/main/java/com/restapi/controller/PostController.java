package com.restapi.controller;

import com.restapi.dto.Post;
import com.restapi.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController implements ApiController<Post>{
    private final RestTemplate restTemplate;
    private final String DEFAULT_URL = "http://jsonplaceholder.typicode.com/posts";

    @GetMapping()
    public ResponseEntity<Post[]> getAll(@RequestParam(required = false) Map<String, String> args) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + subUrl.toString(), Post[].class)
                , HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Post> create(@RequestBody @Validated Post post) {
        return new ResponseEntity<>(restTemplate.postForObject(DEFAULT_URL, post, Post.class)
                , HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getEntity(@PathVariable Long postId) {
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + postId, Post.class)
                , HttpStatus.OK);
    }
    @GetMapping("/{postId}/comments")
    public ResponseEntity<String> getPostComments(@RequestParam(required = false) Map<String, String> args, @PathVariable Long postId) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + postId + "/comments" + subUrl, String.class)
                , HttpStatus.OK);
    }
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updateEntity(@RequestBody @Validated Post post, @PathVariable Long postId) {
        var responsePost = restTemplate.exchange(DEFAULT_URL + "/" + postId,
                HttpMethod.PUT,
                new HttpEntity<Post>(post),
                Post.class);
        return new ResponseEntity<>(responsePost.getBody(), HttpStatus.OK);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Post> patchEntity(@RequestBody @Validated Map<String, ?> args, @PathVariable Long postId) {
        return new ResponseEntity<>(restTemplate.patchForObject(DEFAULT_URL + "/" + postId, args, Post.class)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long postId) {
        restTemplate.delete(DEFAULT_URL + "/" + postId);
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }



    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal User principal) {
        return "You are ADMIN: UserID" + principal.getId();
    }
}
