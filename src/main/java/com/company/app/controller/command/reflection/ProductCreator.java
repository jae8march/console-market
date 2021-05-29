package com.company.app.controller.command.reflection;

import com.company.app.controller.command.template.MethodsCreation;
import com.company.app.entity.Film;
import com.company.app.entity.Frame;
import com.company.app.entity.Photo;
import com.company.app.entity.Series;
import com.company.app.service.CatalogService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * The class gets general information about the product and catalogs it.
 */
public class ProductCreator {
    private final Map<String, Class<? extends Frame>> products = new HashMap<>();
    private final Map<String, MethodsCreation> modes = new HashMap<>();
    private CatalogService catalogService;
    private final int menu = 1;
    private final int errors = 2;
    private final String TYPE_PRODUCT_BUNDLE = "type.product";
    private final String TYPE_CREATION_BUNDLE = "type.creation";
    private final String ERROR_PRODUCT_BUNDLE = "error.type.product";
    private final String ERROR_CREATION_BUNDLE ="error.type.creation";

    public ProductCreator(CatalogService catalogService) {
        products.put("1", Frame.class);
        products.put("2", Film.class);
        products.put("3", Series.class);
        products.put("4", Photo.class);

        modes.put("random", MethodsCreation.RANDOM);
        modes.put("console", MethodsCreation.CONSOLE);

        this.catalogService = catalogService;
    }

    /**
     * Gets general information about the product: how and what to create.
     * Moves information to ProductReflection, creates and catalogs a finished product.
     * @param answer language of interface
     * @return result of work
     */
    public String runner(String answer) {
        String[] locale = answer.split(" ");
        ResourceBundle[] resourceBundles = setLocale(locale[0], locale[1]);

        boolean flag = true;
        Scanner input = new Scanner(System.in);
        Class<? extends Frame> aClass = null;
        MethodsCreation howCreate = null;

        while (flag) {
            System.out.println(resourceBundles[menu].getString(TYPE_PRODUCT_BUNDLE));
            String productType = input.nextLine();

            aClass = products.get(productType);

            if (aClass != null) {
                howCreate = findTypeCreation(input, resourceBundles);
                flag = false;
            } else {
                System.out.println(resourceBundles[errors].getString(ERROR_PRODUCT_BUNDLE));
            }
        }

        ProductReflection productReflection = new ProductReflection(catalogService, howCreate);

        productReflection.createProduct(aClass, resourceBundles);
        return resourceBundles[menu].getString("result");
    }

    private ResourceBundle[] setLocale(String language, String country) {
        Locale current = new Locale(language, country);
        return new ResourceBundle[]{ResourceBundle.getBundle("product", current),
                ResourceBundle.getBundle("menu", current),
                ResourceBundle.getBundle("errors", current)};
    }

    private MethodsCreation findTypeCreation(Scanner input, ResourceBundle[] resourceBundles) {
        boolean flag = true;
        MethodsCreation howCreate = null;
        while (flag) {
            System.out.println(resourceBundles[menu].getString(TYPE_CREATION_BUNDLE));
            String productType = input.nextLine();

            howCreate = modes.get(productType);

            if (howCreate == null) {
                System.out.println(resourceBundles[errors].getString(ERROR_CREATION_BUNDLE));
            } else {
                flag = false;
            }
        }
        return howCreate;
    }
}
