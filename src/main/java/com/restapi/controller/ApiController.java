package com.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
public interface ApiController<T> {
    public ResponseEntity<T[]> getAll(@RequestParam(required = false) Map<String, String> args);

    public ResponseEntity<T> create(@RequestBody @Validated T entity);

    public ResponseEntity getEntity(@PathVariable Long entityId);
    public ResponseEntity<T> updateEntity(@RequestBody @Validated T entity, @PathVariable Long entityId);

    public ResponseEntity<T> patchEntity(@RequestBody @Validated Map<String, ?> args, @PathVariable Long entityId);
    public ResponseEntity<String> deleteEntity(@PathVariable Long entityId);
}