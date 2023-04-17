package com.liliya.shop.repository;

import com.liliya.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select a from Order a where a.user.id =?1")
    List<Order> findByUserId(String id);
}
