package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;

import java.util.Map;
import java.util.Set;

/**
 * Displays a list of products.
 */
public class CatalogCommand implements ICommand {
    private final CatalogService catalogService;

    public CatalogCommand(CatalogService service) {
        catalogService = service;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        Map<Frame, Double> catalog = catalogService.showCatalog();
        Set<Map.Entry<Frame, Double>> set = catalog.entrySet();

        for (int i = 0; i < catalog.size(); i++) {
            Map.Entry mapEntry = (Map.Entry) set.toArray()[i];
            stringBuilder.append("Id: ").append(i).append("    ")
                    .append("Product: ").append(mapEntry.getKey())
                    .append("    ->    Price: ").append(mapEntry.getValue())
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
