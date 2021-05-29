package com.company.app.service;

import com.company.app.entity.Frame;
import com.company.app.repository.CatalogRepository;

import java.util.Map;

/**
 * Service to work with Repository, catalog.
 */
public class CatalogService {
    private CatalogRepository catalogRepository;

    public CatalogService() {
        this.catalogRepository = new CatalogRepository();
    }

    /**
     * Adds product in catalog.
     * @param frame product to add
     * @param price of product
     */
    public void addInCatalog(Frame frame, Double price) {
        catalogRepository.addProduct(frame, price);
    }

    /**
     * Adds old catalog to catalog.
     * @param oldCatalog to add
     */
    public void addAllInCatalog(Map<Frame, Double> oldCatalog) {
        catalogRepository.addAll(oldCatalog);
    }

    /**
     * @return catalog
     */
    public Map<Frame, Double> showCatalog() {
        return catalogRepository.getCatalog();
    }
}
