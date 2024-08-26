package dev.abel.config;


import dev.abel.controller.OrderController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.crac.Context;
import org.crac.Core;
import org.crac.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderPrimingResourceConfig implements Resource {
    private static final Logger logger = LogManager.getLogger();

    private final OrderController orderController;

    public OrderPrimingResourceConfig(OrderController orderController) {
        this.orderController = orderController;
        Core.getGlobalContext().register(this);
    }

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) {
        try {
            orderController.status();
            orderController.createOrder();
        } catch (RuntimeException e) {
            logger.debug(e.getMessage());
        }
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) {
    }
}
