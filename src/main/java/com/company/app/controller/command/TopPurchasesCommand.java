package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.PurchasesService;

import java.util.Map;
import java.util.Set;

/**
 * Displays information about the 5 most recent items that were added to the cart in all shopping sessions.
 */
public class TopPurchasesCommand implements ICommand {
    private final PurchasesService purchasesService;

    public TopPurchasesCommand(PurchasesService service) {
        purchasesService = service;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<Frame, Integer> purchases = purchasesService.showPurchases();

        Set<Map.Entry<Frame, Integer>> set = purchases.entrySet();

        int number = Math.min(purchases.size(), 5);

        for (int i = purchases.size() - 1; i >= purchases.size() - number; i--) {
            Map.Entry mapEntry = (Map.Entry) set.toArray()[i];
            stringBuilder.append("Product: ").append(mapEntry.getKey())
                    .append(", Count: ").append(mapEntry.getValue())
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
