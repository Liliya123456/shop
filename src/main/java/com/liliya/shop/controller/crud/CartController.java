package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.Item;
import com.liliya.shop.entity.Order;
import com.liliya.shop.entity.User;
import com.liliya.shop.repository.ItemRepository;
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
import java.util.ArrayList;
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
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;


    @GetMapping(path = {"", "/"})
    public List<Order> listOrders(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        return orderRepository.findByUserId(user.getUsername());
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order createOrder(@RequestBody Order order, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        String email = user.getUsername();
        User dbUser = userRepository.findById(email).get();
        order.setId(null);
        order.setUser(dbUser);
        isItemExist(order);
        return orderRepository.save(order);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Order findOrderById(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails user, @PathVariable(required = true) Long id) {
        String email = user.getUsername();
        Optional<Order> order = orderRepository.findByUserIdAndId(email, id);
        if (order.isPresent()) {
            return order.get();
        } else
            throw new IllegalArgumentException("Did not find order");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public Order update(@RequestBody Order order, @PathVariable(required = true) Long id, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
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
            isItemExist(order);
            return orderRepository.save(order);
        } else
            throw new IllegalArgumentException("Did not find  order");
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

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable(required = true) Long id, @Parameter(hidden = true) @AuthenticationPrincipal UserDetails user) {
        String email = user.getUsername();
        Optional<Order> order = orderRepository.findByUserIdAndId(email, id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("Did not find order");
    }
}



