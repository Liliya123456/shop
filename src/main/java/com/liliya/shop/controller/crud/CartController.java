package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import com.liliya.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {
    @Autowired
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping(path = {""})
    public List<Order> listOrders(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        return orderRepository.findByUserId(user.getUsername());
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            order.setUser(userById.get());
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Did not find user");

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<Order> readById(@PathVariable(required = true) Long id, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            return orderRepository.findByUserIdAndId(userId, id);
        }
        throw new IllegalArgumentException("Did not find user");

    }

    //TODO не работает ERROR: null value in column "password" of relation "users" violates not-null constraint
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order update(@RequestBody Order order, @PathVariable(required = true) Long id, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        Optional<Order> orderByIdAnduserId = orderRepository.findByUserIdAndId(userId, id);
        if (orderByIdAnduserId.isPresent()) {
            return orderRepository.save(order);
        }
        throw new IllegalArgumentException("Did not find user or order");
    }

    //TODO ругается на удаление но удаляет
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(required = true) Long id, @AuthenticationPrincipal UserDetails user) {
        String userId = user.getUsername();
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isPresent()) {
            orderRepository.deleteById(id);
        }
        throw new IllegalArgumentException("Did not find user or order");

    }

}
