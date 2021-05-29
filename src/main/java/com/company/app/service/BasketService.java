package com.company.app.service;


import com.company.app.entity.Frame;
import com.company.app.repository.BasketRepository;

import java.util.Map;

/**
 * Service to work with Repository, basket.
 */
public class BasketService {
    private final BasketRepository basketRepository;

    public BasketService() {
        basketRepository = new BasketRepository();
    }

    /**
     * @return basket
     */
    public Map<Frame, Integer> showBasket() {
        return basketRepository.getBasket();
    }

    /**
     * Adds product to basket.
     * @param frame product to add
     * @param count of product
     */
    public void addInBasket(Frame frame, Integer count) {
        basketRepository.addProduct(frame, count);
    }

    /**
     * Places an order.
     */
    public void checkout() {
        basketRepository.checkout();
    }
}
