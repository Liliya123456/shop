package com.liliya.shop.repository;

import com.liliya.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);

    //TODO  добавить второй параметр id  исключенного обхекта
}
