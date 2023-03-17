package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public Order createOrder(Order order) {
        return order;
    }

    public Order readById(Long id) {
        return null;
    }

    public Order update(Order order) {
        return order;
    }

    public void deleteOrder(Long id) {

    }


}
