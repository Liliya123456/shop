package com.liliya.shop.service;

import com.liliya.shop.entity.Item;
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

    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public Optional<Item> readById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {
        item.setId(null);
        return itemRepository.save(item);
    }

    public Item updateItem(Item item, Long id) {
        Optional<Item> itemById = itemRepository.findById(item.getId());
        if (!id.equals(item.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body id doesn't match path id");
        } else {
            if (itemById.isPresent()) {
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
