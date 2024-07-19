package com.example.ebooks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ebooks.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT obj FROM Order obj " +
            "JOIN FETCH obj.ebooks JOIN FETCH obj.user WHERE obj.id = :id")
    public Order findByIdCustom(Long id);
}
