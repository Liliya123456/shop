package com.liliya.shop.service;

import com.liliya.shop.entity.Item;
import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.ItemRepository;
import com.liliya.shop.repository.OrderRepository;
import com.liliya.shop.repository.UserRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
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
    public Order createOrder( Order order, UserDetails user) {
        String email = user.getUsername();
        User dbUser = userRepository.findById(email).get();
        order.setId(null);
        order.setUser(dbUser);
        return orderRepository.save(isItemExist(order));
    }
    public Order findOrderById(UserDetails user, Long id) {
        String email = user.getUsername();
        Optional<Order> order = orderRepository.findByUserIdAndId(email, id);
        if (order.isPresent()) {
            return order.get();
        } else
            throw new IllegalArgumentException("Did not find order");
    }
    public Order update(Order order, Long id,  UserDetails user) {
        String email = user.getUsername();
        Optional<Order> dbOrder = orderRepository.findByUserIdAndId(email, id);
        if (dbOrder.isPresent()) {
            order.setId(id);
            order.setUser(dbOrder.get().getUser());

            //TODO выделить проверку по товарам в отдельнеый метод  и добавть в создание.
//            if (order.getItems() == null) {
//                order.setItems(new ArrayList<>(0));
//            } else {
//                List<Item> items = itemRepository.findAllById(getAllid(order.getItems()));
//                if (order.getItems().size() != items.size()) {
//                    throw new IllegalArgumentException("All items must exist");
//                } else {
//                    order.setItems(items);
//                }
//            }
            return orderRepository.save(isItemExist(order));
        } else
            throw new IllegalArgumentException("Did not find  order");
    }

    public void deleteOrder(Long id, UserDetails user) {
        String email = user.getUsername();
        Optional<Order> order = orderRepository.findByUserIdAndId(email, id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Did not find order");
    }


    private List<Long> getAllid(List<Item> items) {
        List<Long> result = new ArrayList<>(items.size());
        for (Item item : items) {
            result.add(item.getId());
        }
        return result;
    }

    private Order isItemExist(Order order) {
        if (order.getItems() == null) {
            order.setItems(new ArrayList<>(0));
        } else {
            List<Item> items = itemRepository.findAllById(getAllid(order.getItems()));
            if (order.getItems().size() != items.size()) {
                throw new IllegalArgumentException("All items must exist");
            } else {
                order.setItems(items);
            }
        }
        return order;
    }



}
