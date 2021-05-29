package com.company.app.repository;

import com.company.app.entity.Frame;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Order Repository.
 */
public class OrderRepository {
    private TreeMap<Date, Map<Frame, Integer>> order = new TreeMap<>();

    /**
     * @return order
     */
    public TreeMap<Date, Map<Frame, Integer>> getOrder() {
        return order;
    }

    /**
     * Places an order, enters into map.
     * @param basket to add
     */
    public void addOrder(Map<Frame, Integer> basket) {
        Date date = new Date();
        order.put(date, basket);
    }
}
