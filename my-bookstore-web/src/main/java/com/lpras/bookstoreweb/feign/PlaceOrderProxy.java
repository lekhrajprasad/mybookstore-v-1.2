package com.lpras.bookstoreweb.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.lpras.dto.OrderInfoDTO;
/*
@FeignClient(name = "MyPlaceOrderMS")
public interface PlaceOrderProxy {
    @GetMapping("/myorders/{userId}")
    public List<OrderInfoDTO> getOrdersByUserId(@PathVariable String userId);

    @GetMapping("/myorder/{orderId}")
    public OrderInfoDTO getOrdersByUserId(@PathVariable Integer orderId);
}
*/
