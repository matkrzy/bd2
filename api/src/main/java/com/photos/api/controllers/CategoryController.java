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
        return ResponseEntity.ok().body(categoryService.getOne(name));
    }

    @GetMapping
    public ResponseEntity getAll() {
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody final Category category) {
        boolean ok = categoryService.addCategory(category);
        if(ok){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
