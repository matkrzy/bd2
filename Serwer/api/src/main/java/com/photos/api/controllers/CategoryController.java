package com.photos.api.controllers;

import com.photos.api.models.Category;
import com.photos.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Micha Królewski on 2018-05-06.
 * @version x
 */

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{name}")
    public ResponseEntity getOne(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOne(name));
    }

    @GetMapping("/{parentName}/{name}")
    public ResponseEntity getOne(@PathVariable String parentName, @PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOne(parentName,name));
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody final Category category) {
        return categoryService.addCategory(category) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
