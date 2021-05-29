package com.company.app.controller;

import com.company.app.controller.command.AddCommand;
import com.company.app.controller.command.AddInCatalogCommand;
import com.company.app.controller.command.BasketCommand;
import com.company.app.controller.command.CatalogCommand;
import com.company.app.controller.command.CheckoutCommand;
import com.company.app.controller.command.DateCommand;
import com.company.app.controller.command.ExitCommand;
import com.company.app.controller.command.FillCatalogCommand;
import com.company.app.controller.command.HelpCommand;
import com.company.app.controller.command.MainCommand;
import com.company.app.controller.command.NotCommand;
import com.company.app.controller.command.OrderCommand;
import com.company.app.controller.command.TopPurchasesCommand;
import com.company.app.service.BasketService;
import com.company.app.service.CatalogService;
import com.company.app.service.OrderService;
import com.company.app.service.PurchasesService;

import java.util.HashMap;
import java.util.Map;

/**
 * Main controller. Processes the input and finds the command.
 */
public class Controller {
    private final Map<String, ICommand> commands = new HashMap<>();

    /**
     * Constructor - fills in the HashMap on creation.
     */
    public Controller() {
        BasketService basketService = new BasketService();
        CatalogService catalogService = new CatalogService();
        PurchasesService purchasesService = new PurchasesService();
        OrderService orderService = new OrderService();

        commands.put("-fillCatalog", new FillCatalogCommand(catalogService));
        commands.put("-catalog", new CatalogCommand(catalogService));
        commands.put("-addProduct", new AddInCatalogCommand(catalogService));
        commands.put("-add", new AddCommand(basketService, purchasesService, catalogService));
        commands.put("-basket", new BasketCommand(basketService));
        commands.put("-checkout", new CheckoutCommand(basketService, orderService, catalogService));
        commands.put("-purchases", new TopPurchasesCommand(purchasesService));
        commands.put("-date", new DateCommand(orderService));
        commands.put("-order", new OrderCommand(orderService, catalogService));
        commands.put("-intro", new MainCommand());
        commands.put("-help", new HelpCommand());
        commands.put("-exit", new ExitCommand(catalogService, purchasesService));
        commands.put(null, new NotCommand());
    }

    /**
     * Splits user input into an array. Finds command from HashMap.
     * @param answer input error, if the user entered a non-existent command,
     *               or a message about the successful execution of the command
     */
    public String process(String answer) {
        String[] words = answer.split(" ");

        if (words.length == 0) {
            return new NotCommand().command(words);
        }

        ICommand iCommand = this.commands.get(words[0]);

        if (iCommand == null) {
            return new NotCommand().command(words);
        }

        return iCommand.command(words);
    }

    /**
     * Displays general information about the application.
     * @return information
     */
    public String intro() {
        return "Welcome to the online store! Console store where you can purchase Frames, Films, Series's and Photos." +
                " Command \"-help\" will display the available commands in the application";
    }
}
