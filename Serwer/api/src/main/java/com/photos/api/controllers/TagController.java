package com.photos.api.controllers;

import com.photos.api.models.Tag;
import com.photos.api.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Micha Królewski on 2018-04-29.
 * @version x
 */

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity getTags() {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity getTag(@PathVariable final String name) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.getTag(name));
    }

    @PostMapping
    public ResponseEntity addTag(@RequestBody final Tag tag) {

        return tagService.addTag(tag) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
