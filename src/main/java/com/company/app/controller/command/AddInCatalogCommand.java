package com.company.app.controller.command;

import com.company.app.controller.ICommand;
import com.company.app.controller.command.template.AbstractProduct;
import com.company.app.controller.command.template.FilmProduct;
import com.company.app.controller.command.template.FrameProduct;
import com.company.app.controller.command.template.MethodsCreation;
import com.company.app.controller.command.template.PhotoProduct;
import com.company.app.controller.command.template.SeriesProduct;
import com.company.app.service.CatalogService;

import java.util.HashMap;
import java.util.Map;

/**
 * Adds a product to the catalog.
 */
public class AddInCatalogCommand implements ICommand {
    private final Map<String, AbstractProduct> product = new HashMap<>();
    private String mode;

    public AddInCatalogCommand(CatalogService catalogService) {
        product.put("Frame random", new FrameProduct(catalogService, MethodsCreation.RANDOM));
        product.put("Frame console", new FrameProduct(catalogService, MethodsCreation.CONSOLE));
        product.put("Film random", new FilmProduct(catalogService, MethodsCreation.RANDOM));
        product.put("Film console", new FilmProduct(catalogService, MethodsCreation.CONSOLE));
        product.put("Photo random", new PhotoProduct(catalogService, MethodsCreation.RANDOM));
        product.put("Photo console", new PhotoProduct(catalogService, MethodsCreation.CONSOLE));
        product.put("Series random", new SeriesProduct(catalogService, MethodsCreation.RANDOM));
        product.put("Series console", new SeriesProduct(catalogService, MethodsCreation.CONSOLE));
    }

    /**
     * {@link ICommand#command(String[])}
     */
    @Override
    public String command(String[] data) {
        if (mode == null) {
            return start(data);
        } else {
            return mode(data);
        }
    }

    public String start(String[] data) {
        if (data.length != 3) {
            return "Invalid number of arguments";
        }

        if (!(data[2].equals("console") || data[2].equals("random"))) {
            return "Wrong input! Enter console or random";
        }

        mode = data[2];

        AbstractProduct abstractProduct = product.get(data[1] + " " + data[2]);

        if (abstractProduct != null) {
            return abstractProduct.createProduct();
        }
        return "Wrong input! Try again";
    }

    private String mode(String[] data) {

        if (data.length != 2) {
            return "Invalid number of arguments";
        }

        AbstractProduct abstractProduct = product.get(data[1] + " " + mode);

        if (abstractProduct != null) {
            return abstractProduct.createProduct();
        }
        return "Wrong input! Try again";
    }
}
