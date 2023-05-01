package com.liliya.shop.service;

import com.liliya.shop.entity.Category;
import com.liliya.shop.entity.Item;
import com.liliya.shop.repository.CategoryRepository;
import com.liliya.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public Optional<Item> readById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        item.setId(null);
        Long categoryId = item.getCategory().getId();
        Optional<Category> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found", new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        } else {
            item.setCategory(existingCategory.get());
        }
        if (!isNameFree(item.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exist");
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Item item, Long id) {
        if (!id.equals(item.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body id doesn't match path id");
        } else {
            Optional<Item> itemById = itemRepository.findById(item.getId());
            if (itemById.isPresent()) {
                Long categoryId = item.getCategory().getId();
                Item existingItem = itemById.get();
                if (existingItem.getCategory().getId() == categoryId) {
                    item.setCategory(existingItem.getCategory());
                } else {
                    Optional<Category> existingCategory = categoryRepository.findById(categoryId);
                    if (existingCategory.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found", new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
                    } else {
                        item.setCategory(existingCategory.get());
                    }
                }
                if (!item.getName().equals(existingItem.getName())) {
                    if (!isNameFree(item.getName())) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Name already exist");
                    }
                }
                return itemRepository.save(item);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item doesn't exist");
        }
    }


    public void deleteItem(Long id) {
        Optional<Item> itemById = itemRepository.findById(id);
        if (itemById.isPresent()) {
            itemRepository.deleteById(id);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item doesn't exist");

    }

    public boolean isNameFree(String newName) {
        return itemRepository.findByName(newName).isEmpty();
    }

}
