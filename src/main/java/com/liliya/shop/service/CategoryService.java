package com.liliya.shop.service;

import com.liliya.shop.entity.Category;
import com.liliya.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<Category> categoryByName = categoryRepository.findByName(category.getName());
        if (!categoryByName.isPresent()) {
            return categoryRepository.save(category);
        } else
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exist");
    }

    public Category update(Category category, Long id) {
        if (!id.equals(category.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body id doesn't match path id");
        } else {
            Optional<Category> categoryById = categoryRepository.findById(category.getId());
            if (categoryById.isPresent()) {
                if (!category.getName().equals(categoryById.get().getName())) {
                    if (!isNameFree(category.getName())) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exist");
                    }
                }
                return categoryRepository.save(category);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }

    public void deleteCategory(@PathVariable(required = true) Long id) {

        Optional<Category> categoryById = categoryRepository.findById(id);
        if (categoryById.isPresent()) {
            categoryRepository.deleteById(id);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
    }

    private boolean isNameFree(String newName) {
        return categoryRepository.findByName(newName).isEmpty();
    }


}
