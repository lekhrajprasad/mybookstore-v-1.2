package com.lpras.placeorder.dao;

import com.lpras.placeorder.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> getOrderItemByOrderId(Integer orderId);
}
