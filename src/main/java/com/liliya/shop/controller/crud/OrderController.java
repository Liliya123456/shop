package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import com.liliya.shop.service.OrderService;
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

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(path = {"/", ""})
    public List<Order> listOrders() {
        return orderService.listOrders();
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Order> readById(@PathVariable(required = true) Long id) {
        return orderService.readById(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order update(@RequestBody Order order, @PathVariable(required = true) Long id) {
        return orderService.update(order,id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(required = true) Long id) {
         orderService.deleteOrder(id);

    }


}
