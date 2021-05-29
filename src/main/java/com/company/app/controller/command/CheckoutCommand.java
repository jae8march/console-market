package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.BasketService;
import com.company.app.service.CatalogService;
import com.company.app.service.OrderService;

import java.util.Map;
import java.util.Set;

/**
 * Places an order, buy all the products from the basket.
 */
public class CheckoutCommand implements ICommand {
    private final BasketService basketService;
    private final OrderService orderService;
    private final CatalogService catalogService;

    public CheckoutCommand(BasketService serviceBasket, OrderService serviceOrder, CatalogService serviceCatalog) {
        basketService = serviceBasket;
        orderService = serviceOrder;
        catalogService = serviceCatalog;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Map<Frame, Integer> basket = basketService.showBasket();
        StringBuilder stringBuilder = new StringBuilder();
        double price = 0;

        Set<Map.Entry<Frame, Integer>> set = basket.entrySet();
        Map<Frame, Double> catalog = catalogService.showCatalog();

        for (Map.Entry<Frame, Integer> element : set) {
            price += element.getValue() * catalog.get(element.getKey());
        }

        price = (double) Math.round(price * 100) / 100;
        stringBuilder.append("Total price: ").append(price);

        orderService.checkout(basket);
        basketService.checkout();

        return stringBuilder.toString();
    }
}
