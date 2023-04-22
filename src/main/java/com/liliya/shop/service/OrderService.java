package com.liliya.shop.service;

import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        String id = order.getUser().getId();
        em.merge(order);
        em.persist(order);
        em.flush();
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            order.setUser(byId.get());
            return orderRepository.save(order);
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Did not find user");
    }

    public Optional<Order> readById(Long id) {
        return orderRepository.findById(id);
    }

    //TODO проверка на существование
    public Order update(Order order, Long id) {
        if (!id.equals(order.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body id doesn't match path id");
        }
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find order");

    }
}
