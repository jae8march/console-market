package com.company.app.service;

import com.company.app.entity.Frame;
import com.company.app.repository.PurchasesRepository;

import java.util.Map;

/**
 * Service to work with Repository, purchases.
 */
public class PurchasesService {
    private PurchasesRepository purchasesRepository;

    public PurchasesService() {
        this.purchasesRepository = new PurchasesRepository();
    }

    /**
     * @return purchases
     */
    public Map<Frame, Integer> showPurchases() {
        return purchasesRepository.getPurchases();
    }

    /**
     * Adds product to purchase history.
     * @param frame product to add
     * @param count of product
     */
    public void addInHistory(Frame frame, Integer count) {
        purchasesRepository.addPurchases(frame, count);
    }
}
