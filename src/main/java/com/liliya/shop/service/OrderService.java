package com.liliya.shop.service;

import com.liliya.shop.entity.Item;
import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        String userId = order.getUser().getId();
        Optional<User> byId = userRepository.findById(userId);
        if (byId.isPresent()) {
            order.setUser(byId.get());
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Did not find user");
        order.setId(null);
        order.setItems(loadAndCountItems(getAllid(order.getItems())));
        return orderRepository.save(order);
    }

    public Optional<Order> readById(Long id) {
        return orderRepository.findById(id);
    }

    public Order update(Order order, Long id) {
        if (!id.equals(order.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Body id doesn't match path id");
        }
        Optional<Order> dbOrder = orderRepository.findById(id);
        if (dbOrder.isPresent()) {
            order.setId(id);
            order.setUser(dbOrder.get().getUser()); //Не изменяем пользователя???
            order.setItems(loadAndCountItems(getAllid(order.getItems())));
            return orderRepository.save(order);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find order");
    }


    public void deleteOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find order");

    }

    private List<Long> getAllid(List<Item> items) {
        if (items == null)
            return new ArrayList<>(0);
        List<Long> result = new ArrayList<>(items.size());
        for (Item item : items) {
            result.add(item.getId());
        }
        return result;
    }

    private List<Item> loadAndCountItems(List<Long> ids) {
        List<Item> items = itemRepository.findAllById(ids);
        if (items.size() != ids.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All items must exist", new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        }
        return items;
    }
}
