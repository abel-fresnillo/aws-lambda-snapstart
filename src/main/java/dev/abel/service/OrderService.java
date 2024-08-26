package dev.abel.service;

import dev.abel.model.Order;
import dev.abel.repository.DynamoDBOrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final DynamoDBOrderRepository dynamoDBOrderRepository;

    public OrderService(DynamoDBOrderRepository dynamoDBOrderRepository) {
        this.dynamoDBOrderRepository = dynamoDBOrderRepository;
    }

    public Order createOrder() {
        String orderId = UUID.randomUUID().toString();

        Order order = new Order();
        order.setId(orderId);

        return dynamoDBOrderRepository.save(order);
    }
}
