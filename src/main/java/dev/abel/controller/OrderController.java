package dev.abel.controller;

import dev.abel.model.Order;
import dev.abel.service.OrderService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public String status() {
        return "HEALTHY";
    }

    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public Order createOrder() {
        return orderService.createOrder();
    }
}
