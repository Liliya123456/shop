package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CRUD
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(path = {"/", ""})
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order) {
        return order;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Order readById(@PathVariable(required = true) Long id) {
        return null;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order update(@RequestBody Order order, @PathVariable(required = true) Long id) {
        if (!id.equals(order.getId())) {
            throw new IllegalArgumentException("Id is not match!");
        }
        return orderRepository.save(order);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(required = true) Long id) {

    }


}
