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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable final Long id) {
        return categoryService.deleteCategory(id) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{parentId}")
    public ResponseEntity getCategories(@PathVariable final Long parentId) {
        List<Category> categories = categoryService.getAll(parentId);
        return categories != null ?
                ResponseEntity.status(HttpStatus.OK).body(categories) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity getCategories() {
        List<Category> categories = categoryService.getAll(null);
        return categories != null ?
                ResponseEntity.status(HttpStatus.OK).body(categories) :
                ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody final Category category) {
        return categoryService.addCategory(category) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity editCategory(@PathVariable final Long id, @RequestBody final Category category) {
        return categoryService.editCategory(id, category) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
