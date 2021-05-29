package com.company.app.repository;

import com.company.app.entity.Frame;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Catalog Repository.
 */
public class CatalogRepository {
    private Map<Frame, Double> catalog = new LinkedHashMap<>();

    /**
     * Adds product in catalog. If product has in map, sums up its old and new price.
     * @param frame product to add
     * @param price of product
     */
    public void addProduct(Frame frame, Double price) {
        catalog.put(frame, price);
    }

    /**
     * Adds old catalog to catalog.
     * @param oldCatalog to add
     */
    public void addAll(Map<Frame, Double> oldCatalog) {
        catalog.putAll(oldCatalog);
    }

    /**
     * @return catalog
     */
    public Map<Frame, Double> getCatalog() {
        return catalog;
    }
}
