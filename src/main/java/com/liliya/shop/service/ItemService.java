package com.liliya.shop.service;

import com.liliya.shop.entity.Item;
import com.liliya.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (itemById.isPresent()) {
            return itemRepository.save(item);
        }
        throw new IllegalArgumentException("Item doesnt exist!");

    }

    public void deleteItem(Long id) {
        Optional<Item> itemById = itemRepository.findById(id);
        if (itemById.isPresent()) {
            itemRepository.deleteById(id);
        } else throw new IllegalArgumentException("Item doesnt exist!");
    }

    public boolean isNameFree(String newName) {
        return itemRepository.findByName(newName).isEmpty();
    }

}
