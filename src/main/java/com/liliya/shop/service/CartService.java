package com.liliya.shop.service;

import com.liliya.shop.entity.Item;
import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;


    public List<Order> listOrders(UserDetails user) {
        return orderRepository.findByUserId(user.getUsername());
    }

    public Order createOrder(Order order, String username) {
        User dbUser = userRepository.findById(username).get();
        order.setId(null);
        order.setUser(dbUser);
        order.setItems(loadAndCountItems(getAllid(order.getItems())));
        return orderRepository.save(order);
    }

    public Order findOrderById(Long id, String username) {
        Optional<Order> order = orderRepository.findByUserIdAndId(username, id);
        if (order.isPresent()) {
            return order.get();
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find order");
    }

    public Order update(Order order, Long id, String username) {
        Optional<Order> dbOrder = orderRepository.findByUserIdAndId(username, id);
        if (dbOrder.isPresent()) {
            order.setId(id);
            order.setUser(dbOrder.get().getUser());
            order.setItems(loadAndCountItems(getAllid(order.getItems())));
            return orderRepository.save(order);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find order");
    }

    public void deleteOrder(Long id, String username) {
        Optional<Order> order = orderRepository.findByUserIdAndId(username, id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
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

    //Стыд и позор
//
//    private Order isItemExist(Order order) {
//        if (order.getItems() == null) {
//            order.setItems(new ArrayList<>(0));
//        } else {
//            List<Item> items = itemRepository.findAllById(getAllid(order.getItems()));
//            if (order.getItems().size() != items.size()) {
//                throw new NotFoundException("All items must exist");
//            } else {
//                order.setItems(items);
//            }
//        }
//        return order;
//    }
    private List<Item> loadAndCountItems(List<Long> ids) {
        List<Item> items = itemRepository.findAllById(ids);
        if (items.size() != ids.size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All items must exist", new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        }
        return items;
    }


}
