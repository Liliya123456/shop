package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//CRUD
@RequestMapping(path = "/api/order", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/", ""})
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order) {
        String id = order.getUser().getId();
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            order.setUser(byId.get());
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Did not find user");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Order> readById(@PathVariable(required = true) Long id) {
        return orderRepository.findById(id);
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
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        }

    }


}
