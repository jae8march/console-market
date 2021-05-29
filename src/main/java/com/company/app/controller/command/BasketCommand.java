package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.BasketService;

import java.util.Map;
import java.util.Set;

/**
 * Displays the contents of the basket.
 */
public class BasketCommand implements ICommand {
    private final BasketService basketService;

    public BasketCommand(BasketService service) {
        basketService = service;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<Frame, Integer> basket = basketService.showBasket();
        Set<Map.Entry<Frame, Integer>> set = basket.entrySet();

        for (Map.Entry<Frame, Integer> element : set) {
            stringBuilder.append("Product: ").append(element.getKey())
                    .append("    ->    Count: ").append(element.getValue())
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
