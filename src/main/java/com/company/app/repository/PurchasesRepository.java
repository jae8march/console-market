package com.company.app.repository;

import com.company.app.entity.Frame;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Purchases Repository.
 */
public class PurchasesRepository {
    private final Map<Frame, Integer> purchases = new LinkedHashMap<>();

    /**
     * @return purchases
     */
    public Map<Frame, Integer> getPurchases() {
        return purchases;
    }

    /**
     * Adds product to map. If product has in map, If product has in map, sums up its old and new price,
     * puts it on last position.
     * @param frame product to add
     * @param count of product
     */
    public void addPurchases(Frame frame, Integer count) {
        if (purchases.containsKey(frame)) {
            count += purchases.remove(frame);
        }
        purchases.put(frame, count);
    }
}
