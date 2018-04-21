package com.photos.api.services;

import com.photos.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Micha Królewski on 2018-04-21.
 * @version 1.0
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
}
