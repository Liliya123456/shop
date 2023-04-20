package com.liliya.shop.service;

import com.liliya.shop.entity.Category;
import com.liliya.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    public Optional<Category> readById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category, Long id) {
        Optional<Category> categoryById = categoryRepository.findById(category.getId());
        if (categoryById.isPresent()) {
            return categoryRepository.save(category);
        } else throw new IllegalArgumentException("Id is not match!");
    }

    public void deleteCategory(@PathVariable(required = true) Long id) {

        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()) {
            categoryRepository.deleteById(id);
        } else throw new IllegalArgumentException("Id doesnt exist!");
    }

}
