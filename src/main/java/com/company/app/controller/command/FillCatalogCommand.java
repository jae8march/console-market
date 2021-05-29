package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Filling product data into the catalog.
 */
public class FillCatalogCommand implements ICommand {
    private final CatalogService catalogService;

    public FillCatalogCommand(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        Map<Frame, Double> catalog = getData();
        if (catalog == null) {
            return "Catalog is empty";
        }
        catalogService.addAllInCatalog(catalog);
        return "The catalog has been filled.";
    }

    private Map<Frame, Double> getData(){
        Map<Frame, Double> basket = null;

        try (FileInputStream fileInputStream = new FileInputStream("savedCatalog");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            basket = (Map<Frame, Double>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
        return basket;
    }

}
