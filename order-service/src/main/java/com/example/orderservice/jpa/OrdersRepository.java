package com.example.orderservice.jpa;

import org.springframework.data.repository.CrudRepository;



public interface OrdersRepository extends CrudRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);
    Iterable<OrderEntity> findByUserId(String orderId);
}