package dev.abel.repository;

import dev.abel.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Repository
public class DynamoDBOrderRepository {
    private static final Logger logger = LogManager.getLogger();

    private final DynamoDbTable<Order> orderDynamoDbTable;

    public DynamoDBOrderRepository(DynamoDbTable<Order> orderDynamoDbTable) {
        this.orderDynamoDbTable = orderDynamoDbTable;
    }

    public Order save(Order order) {
        try {
            orderDynamoDbTable.putItem(order);
            logger.info("Order with id {} saved successfully", order.getId());
            return order;
        } catch (DynamoDbException e) {
            logger.error("Order with id {} could not be saved to DynamoDB", order.getId(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
