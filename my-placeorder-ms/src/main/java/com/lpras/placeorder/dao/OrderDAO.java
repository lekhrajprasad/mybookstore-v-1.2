package com.lpras.placeorder.dao;

import com.lpras.placeorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    public List<Order> findOrderByUserId(String userId);
}
