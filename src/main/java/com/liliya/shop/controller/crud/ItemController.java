package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Item;
import com.liliya.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/api/item", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path = {"/", ""})
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Item> readById(@PathVariable(required = true) Long id) {
        return itemRepository.findById(id);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Item createItem(@RequestBody Item item) {
        return item;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Item update(@RequestBody Item item, @PathVariable(required = true) Long id) {
        if (!id.equals(item.getId())) {
            throw new IllegalArgumentException("Id is not match!");
        }
        return itemRepository.save(item);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable(required = true) Long id) {

    }


}
