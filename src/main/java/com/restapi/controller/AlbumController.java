package com.restapi.controller;

import com.restapi.dto.Album;
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
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController implements ApiController<Album> {
    private final RestTemplate restTemplate;
    private final String DEFAULT_URL = "http://jsonplaceholder.typicode.com/albums";

    @Override
    @GetMapping()
    public ResponseEntity<Album[]> getAll(@RequestParam(required = false) Map<String, String> args) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a, b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + subUrl.toString(), Album[].class)
                , HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<Album> create(@RequestBody @Validated Album album) {
        return new ResponseEntity<>(restTemplate.postForObject(DEFAULT_URL, album, Album.class)
                , HttpStatus.OK);
    }

    @Override
    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getEntity(@PathVariable Long albumId) {
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + albumId, Album.class)
                , HttpStatus.OK);
    }

    @Override
    @PutMapping("/{albumId}")
    public ResponseEntity<Album> updateEntity(@RequestBody @Validated Album album, @PathVariable Long albumId) {
        var responsePost = restTemplate.exchange(DEFAULT_URL + "/" + albumId,
                HttpMethod.PUT,
                new HttpEntity<Album>(album),
                Album.class);
        return new ResponseEntity<>(responsePost.getBody(), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{albumId}")
    public ResponseEntity<Album> patchEntity(@RequestBody @Validated Map<String, ?> args, @PathVariable Long albumId) {
        return new ResponseEntity<>(restTemplate.patchForObject(DEFAULT_URL + "/" + albumId, args, Album.class)
                , HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{albumId}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long albumId) {
        restTemplate.delete(DEFAULT_URL + "/" + albumId);
        return new ResponseEntity<>("Delete successfull", HttpStatus.OK);
    }

    @GetMapping("/{albumId}/photos")
    public ResponseEntity<String> getAlbumPhotos(@RequestParam(required = false) Map<String, String> args, @PathVariable Long albumId) {
        StringBuilder subUrl = new StringBuilder("?");
        args.forEach((a,b) -> {
            subUrl.append(a).append('=').append(b).append('&');
        });
        return new ResponseEntity<>(restTemplate.getForObject(DEFAULT_URL + "/" + albumId + "/photos" + subUrl, String.class)
                , HttpStatus.OK);
    }
}
