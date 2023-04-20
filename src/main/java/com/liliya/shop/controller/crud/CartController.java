package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Order;
import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import com.liliya.shop.service.CartService;
import com.liliya.shop.service.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/cart", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;


    @GetMapping(path = {""})
    public List<Order> listOrders(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        return cartService.listOrders(user);

    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        return cartService.createOrder(order, user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Order findOrderById(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user, @PathVariable(required = true) Long id) {
        return cartService.findOrderById(user, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order update(@RequestBody Order order, @PathVariable(required = true) Long id, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        return cartService.update(order, id, user);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(required = true) Long id, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        cartService.deleteOrder(id, user);
    }
}



