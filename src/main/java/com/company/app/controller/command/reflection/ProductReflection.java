package com.company.app.controller.command.reflection;

import com.company.app.controller.command.template.AbstractProduct;
import com.company.app.controller.command.template.MethodsCreation;
import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;
import com.company.app.util.Trivial;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Class for working with reflection.
 * Finds out the methods of the product class, gets out its annotations.
 * Creates and adds a product to the catalog
 */
public class ProductReflection extends AbstractProduct {
    private ArrayList<Method> methods;
    private ResourceBundle[] resourceBundles;
    private Frame goods;
    private final int product = 0;
    private final int errors = 2;

    public ProductReflection(CatalogService catalogService, MethodsCreation howCreate) {
        super(catalogService, howCreate);
    }

    /**
     * Finds list of product class setters
     * Choosing a way to create a product.
     */
    public void createProduct(Class<? extends Frame> productClass, ResourceBundle[] resourceBundles) {
        methods = getProductMethods(productClass);
        this.resourceBundles = resourceBundles;

        try {
            goods = (Frame) Class.forName(productClass.getName()).getConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | ClassNotFoundException
                | IllegalAccessException | InstantiationException exception) {
            exception.printStackTrace();
        }

        if (howCreate.equals(MethodsCreation.RANDOM)) {
            randomProduct();
        } else {
            consoleProduct();
        }
    }

    /**
     * {@link AbstractProduct#randomProduct()}
     */
    @Override
    public void randomProduct() {
        for (Method method : methods) {
            String message = resourceBundles[product].getString(getProductAnnotation(method));
            Class type = method.getParameterTypes()[0];
            try {
                if (type == int.class) {
                    method.invoke(goods, new Random().nextInt(10001));
                } else if (type == String.class) {
                    message += new Random().nextInt(10001);
                    method.invoke(goods, message);
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }

        catalogService.addInCatalog(goods, randomPrice());
    }

    /**
     * {@link AbstractProduct#consoleProduct()}
     */
    @Override
    public void consoleProduct() {
        Scanner input = new Scanner(System.in);

        for (Method method : methods) {
            String message = resourceBundles[product].getString(getProductAnnotation(method));
            Class type = method.getParameterTypes()[0];
            try {
                if (type == int.class) {
                    method.invoke(goods, consoleInt(message, input,
                            resourceBundles[errors].getString("error.not.int")));
                } else if (type == String.class) {
                    System.out.print(message + ": ");
                    method.invoke(goods, input.nextLine());
                }
            } catch (IllegalAccessException | InvocationTargetException exception) {
                exception.printStackTrace();
            }
        }

        double price = consoleDouble(resourceBundles[product].getString("product.price"), input,
                resourceBundles[errors].getString("error.not.double"));

        catalogService.addInCatalog(goods, price);
    }

    private String getProductAnnotation(Method method) {
        return method.getAnnotation(Trivial.class).field();
    }

    private ArrayList<Method> getProductMethods(Class<?> productClass) {
        Method[] declaredMethods = productClass.getMethods();
        ArrayList<Method> setMethods = new ArrayList<>();
        for (Method method : declaredMethods) {
            if (method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
                setMethods.add(method);
            }
        }
        return setMethods;
    }
}
