package com.lpras.placeorder.controller;

import com.lpras.dto.OrderInfoDTO;
import com.lpras.placeorder.entity.Order;
import com.lpras.placeorder.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RefreshScope
public class OrderController {
    static Logger log = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    OrderService orderService;
    
    @Value("${server.port}")
    String port;
    
    @Value("${placeorder.cloudconfig.msgupdated:defaultMsg}")
    String msg;

	/*
	 * @PutMapping(value = "/placeOrder") public void placeOrder(@RequestBody
	 * OrderInfoDTO orderInfo){ orderService.placeOrder(orderInfo); }
	 */
    
    @GetMapping(value = "/myorders/{userId}")
    public List<Order> getOrderByUserId(@PathVariable String userId){
    	System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        List<Order> orderList = orderService.getOrderByUserId(userId);
        return orderList;
    }

    @GetMapping(value = "/myorder/{orderId}")
    public Order getOrderByUserId(@PathVariable Integer orderId){
    	System.out.println("=============================::port:::::"+port);
        System.out.println("=============================::msg:::::"+msg);
        Order order = orderService.getOrderByOrderId(orderId);
        return order;
    }
}
