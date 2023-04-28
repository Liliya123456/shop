package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Category;
import com.liliya.shop.repository.CategoryRepository;
import com.liliya.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RequestMapping(path = "/api/category", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = {""})
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Category> readById(@PathVariable(required = true) Long id) {
        return categoryService.readById(id);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Category update(@RequestBody Category category, @PathVariable(required = true) Long id) {
        return categoryService.update(category, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable(required = true) Long id) {
        categoryService.deleteCategory(id);
    }

}
