package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;
import com.company.app.service.PurchasesService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Exiting the program.
 */
public class ExitCommand implements ICommand {
    private final CatalogService catalogService;
    private final PurchasesService purchasesService;

    public ExitCommand(CatalogService catalogService, PurchasesService purchasesService) {
        this.catalogService = catalogService;
        this.purchasesService = purchasesService;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Map<Frame, Double> catalog = catalogService.showCatalog();
        Map<Frame, Integer> purchases = purchasesService.showPurchases();

        if (!catalog.isEmpty()) {
            savedDataCatalog(catalog);
        }

        if (!purchases.isEmpty()) {
            savedDataPurchases(purchases);
        }
        return "false";
    }

    private void savedDataCatalog(Map<Frame, Double> catalog){
        try (FileOutputStream outputStream = new FileOutputStream("savedCatalog");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(catalog);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void savedDataPurchases(Map<Frame, Integer> purchases){
        try (FileOutputStream outputStream = new FileOutputStream("savedPurchases");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(purchases);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
