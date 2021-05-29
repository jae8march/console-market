package com.company.app.repository;


import com.company.app.entity.Frame;

import java.util.HashMap;
import java.util.Map;

/**
 * Basket Repository.
 */
public class BasketRepository {
    private Map<Frame, Integer> basket = new HashMap<>();

    /**
     * @return basket
     */
    public Map<Frame, Integer> getBasket() {
        return basket;
    }

    /**
     * Adds product to basket. If product has in map, updates its count.
     * @param frame product to add
     * @param count of product
     */
    public void addProduct(Frame frame, Integer count) {
        if (basket.containsKey(frame)) {
            count += basket.remove(frame);
        }
        basket.put(frame, count);
    }

    /**
     * Places an order.
     */
    public void checkout() {
        basket = new HashMap<>();
    }
}
