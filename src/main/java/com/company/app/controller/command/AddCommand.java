package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.BasketService;
import com.company.app.service.CatalogService;
import com.company.app.service.PurchasesService;

import java.util.Map;

/**
 * Adds a product to the cart.
 */
public class AddCommand implements ICommand {
    private static final String REGEX_INT = "(?<![-.])(\\b[0-9]+\\b)(?!\\.[0-9])";

    private final BasketService basketService;
    private final PurchasesService purchasesService;
    private final CatalogService catalogService;

    public AddCommand(BasketService serviceBasket, PurchasesService servicePurchases, CatalogService serviceCatalog) {
        basketService = serviceBasket;
        purchasesService = servicePurchases;
        catalogService = serviceCatalog;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Map<Frame, Double> catalog = catalogService.showCatalog();

        if (data.length != 3) {
            return "Enter all information about product: id and number of product";
        }

        if (isNotInt(data[1]) || isNotInt(data[2])) {
            return "Entered number or numbers are not integers";
        }

        int id = Integer.parseInt(data[1]);
        int count = Integer.parseInt(data[2]);

        if (id < 0 || id > catalog.size()) {
            return "Invalid product id: no such id exists";
        }

        if (count < 0) {
            return "Entered the amount must be greater than zero or equal to zero";
        }

        Frame frame = (Frame) catalog.keySet().toArray()[id];
        basketService.addInBasket(frame, count);
        purchasesService.addInHistory(frame, count);

        return "Product was successfully added to basket!";
    }

    private boolean isNotInt(String string) {
        return !string.matches(REGEX_INT);
    }
}
