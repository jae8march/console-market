package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.controller.command.reflection.ProductCreator;
import com.company.app.service.CatalogService;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Command with switching to an online store with reflection.
 */
public class ReflectionCommand implements ICommand {
    private CatalogService catalogService;
    private HashSet<String> language = new HashSet<>();

    public ReflectionCommand(CatalogService catalogService) {
        language.add("en US");
        language.add("ru RU");

        this.catalogService = catalogService;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        String info = null;

        while (flag) {
            System.out.println("Select the interface language:");
            System.out.println("English - 'en US'");
            System.out.println("Russian - 'ru RU'");
            String resultInput = scanner.nextLine();
            if (!language.contains(resultInput)) {
                System.out.println("You must enter 'en US' or 'ru RU'!");
            } else {
                info = new ProductCreator(catalogService).runner(resultInput);
                flag = false;
            }
        }
        return info;
    }
}
