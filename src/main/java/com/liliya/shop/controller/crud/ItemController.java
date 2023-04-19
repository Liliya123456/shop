package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Item;
import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.service.ItemService;
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
    @Autowired
    private ItemService itemService;

    @GetMapping(path = {"/", ""})
    public List<Item> itemList() {
        return itemService.itemList();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Item> readById(@PathVariable(required = true) Long id) {
        return itemService.readById(id);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Item update(@RequestBody Item item, @PathVariable(required = true) Long id) {
        return itemService.updateItem(item,id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable(required = true) Long id) {

    }


}
