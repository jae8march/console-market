package com.company.app.controller.command;

import com.company.app.controller.ICommand;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Lists the available commands and their attributes.
 */
public class HelpCommand  implements ICommand {
    private final Map<String, String> manual = new LinkedHashMap<>();

    /**
     * Constructor - fills in the HashMap on creation.
     */
    public HelpCommand() {
        manual.put("-catalog", "Displays a list of products");
        manual.put("-addProduct", "Adds a product to the catalog. The first parameter is the product type. " +
                "The second parameter is console or random.");
        manual.put("-add", "Adds a product to the cart");
        manual.put("-basket", "Views cart contents");
        manual.put("-checkout", "Checkouts and displays the total order amount");
        manual.put("-purchases", "Views information about the last 5 items that were added to the cart in all shopping sessions");
        manual.put("-date", "Views the list of orders for a specified period of time");
        manual.put("-order", "Selections of an order by the nearest date");
        manual.put("-intro", "Displays general information about the application");
        manual.put("-help", "Lists the available commands and their attributes");
        manual.put("-exit", "Exits the program");
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        manual.forEach((k, v) -> stringBuilder
                .append(k).append("   ->  ")
                .append(v)
                .append(System.lineSeparator()));
        return stringBuilder.toString();
    }
}
