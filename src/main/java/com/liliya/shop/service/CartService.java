package com.liliya.shop.service;

import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class CartService {
    @Autowired
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;
}
