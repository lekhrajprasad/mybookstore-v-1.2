package com.lpras.dto;

import java.io.Serializable;
import java.util.List;

public class OrderInfoDTO implements Serializable {
    private static final long serialVersionUID = -4604746091153374567L;
    private OrderDTO orderDTO;
    private List<OrderItemDTO> itemListDTO;

    public OrderInfoDTO() {
	super();
    }

    public OrderDTO getOrderDTO() {
	return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
	this.orderDTO = orderDTO;
    }

    public List<OrderItemDTO> getItemListDTO() {
	return itemListDTO;
    }

    public void setItemListDTO(List<OrderItemDTO> itemListDTO) {
	this.itemListDTO = itemListDTO;
    }

}
