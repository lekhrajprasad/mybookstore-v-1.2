package com.lpras.placeorder.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lpras.dto.BookInventoryInfoDTO;
import com.lpras.dto.OrderDTO;
import com.lpras.dto.OrderInfoDTO;
import com.lpras.dto.OrderItemDTO;
import com.lpras.placeorder.dao.BookInventoryDAO;
import com.lpras.placeorder.dao.OrderDAO;
import com.lpras.placeorder.dao.OrderItemDAO;
import com.lpras.placeorder.entity.BookInventory;
import com.lpras.placeorder.entity.Order;
import com.lpras.placeorder.entity.OrderItem;
import com.lpras.placeorder.util.StringConstant;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderItemDAO orderItemDAO;

    @Autowired
    BookInventoryDAO bookInventoryDAO;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @RabbitListener(queues = StringConstant.ORDER_QUEUE)
    public void placeOrder(OrderInfoDTO orderInfo) {
        //4 task to do when you place the order
        //1. Insert Order - 1
        OrderDTO orderDTO = orderInfo.getOrderDTO();
        Order order = new Order();
        order.setOrderDate(orderDTO.getOrderDate());
        order.setUserId(orderDTO.getUserId());
        order.setTotalQty(orderDTO.getTotalQty());
        order.setTotalCost(orderDTO.getTotalPrice());
        order.setStatus(orderDTO.getStatus());
        
        order = orderDAO.save(order);//1002
        
        int orderId = order.getOrderId();
        //2. Insert order items - N
        //Need to improve for performance
        List<OrderItemDTO> itemList = orderInfo.getItemListDTO();
        for(OrderItemDTO orderItemDTO: itemList){
        	OrderItem orderItem = new OrderItem(orderId, orderItemDTO.getBookId(), orderItemDTO.getQty(), orderItemDTO.getCost());
            orderItemDAO.save(orderItem);
        }
        //3. update local BookInventry -N
        //4. Update BookSearchMS BookInventry-N
        //TODO: Need to improve this
        for(OrderItemDTO orderItemDTO:itemList){
            Integer bookId = orderItemDTO.getBookId();
            BookInventory bookInventory = bookInventoryDAO.findById(bookId).get();
            Integer currentStock = bookInventory.getBooksAvailable();
            currentStock = currentStock - orderItemDTO.getQty();
            //updating
            bookInventory.setBooksAvailable(currentStock);

            //Local Inventory
            bookInventoryDAO.save(bookInventory);
            //Inventory of BooksearchMS
            //Update Inventory of BookSearchMS by Sending Message
            BookInventoryInfoDTO bookInventoryInfoDTO = new BookInventoryInfoDTO();
            bookInventoryInfoDTO.setBookId(bookInventory.getBookId());
            bookInventoryInfoDTO.setBooksAvailable(bookInventory.getBooksAvailable());
            rabbitTemplate.convertAndSend(StringConstant.INVENTORY_QUEUE, bookInventoryInfoDTO);
            
        }
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        List<Order> orderList = orderDAO.findOrderByUserId(userId);
        return orderList;
    }

    @Override
    public Order getOrderByOrderId(Integer orderId) {
        Order order = orderDAO.findById(orderId).get();
        return order;
    }
}
