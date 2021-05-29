package com.company.app.service;


import com.company.app.entity.Frame;
import com.company.app.repository.OrderRepository;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Service to work with Repository, order.
 */
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    /**
     * Places an order.
     * @param basket to add
     */
    public void checkout(Map<Frame, Integer> basket) {
        orderRepository.addOrder(basket);
    }

    /**
     * @return order
     */
    public TreeMap<Date, Map<Frame, Integer>> showOrders() {
        return orderRepository.getOrder();
    }
}
