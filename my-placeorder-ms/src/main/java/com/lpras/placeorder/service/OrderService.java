package com.lpras.placeorder.service;

import java.util.List;

import com.lpras.placeorder.entity.Order;

public interface OrderService {
    public List<Order> getOrderByUserId(String userId);
    public Order getOrderByOrderId(Integer orderId);
}
