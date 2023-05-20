package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import org.springframework.stereotype.Service;


public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> gerOrdersByUserId(String userId);

    OrderDto deleteOrder(String orderId);
}
