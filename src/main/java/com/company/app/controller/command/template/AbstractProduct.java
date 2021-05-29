package com.company.app.controller.command.template;

import static com.company.app.util.Validation.isDouble;
import static com.company.app.util.Validation.isInt;

import com.company.app.service.CatalogService;
import java.util.Scanner;

/**
 * The main template for collecting information and cataloging a product.
 */
public abstract class AbstractProduct {
    protected CatalogService catalogService;
    protected MethodsCreation howCreate;
    protected String intError = "You must enter an integer!";
    protected String doubleError = "You must enter an integer or floating point!";
    protected String info = "Product was successfully added to catalog!";

    protected AbstractProduct(CatalogService catalogService, MethodsCreation howCreate) {
        this.catalogService = catalogService;
        this.howCreate = howCreate;
    }

    /**
     * Random product creation and cataloging.
     */
    public abstract void randomProduct();

    /**
     * Self-created product and cataloging.
     */
    public abstract  void consoleProduct();

    /**
     * Choosing a way to create a product.
     * @return result of working
     */
    public String createProduct() {
        if (howCreate.equals(MethodsCreation.RANDOM)) {
            randomProduct();
        } else {
            consoleProduct();
        }
        return info;
    }

    /**
     * Method that accepts values from the user and checks if the value is double.
     * @param message what the user should enter
     * @param input an instance of the class for entering information
     * @param error message if input is not Double
     * @return double from user
     */
    public double consoleDouble(String message, Scanner input, String error) {
        double doub = 0;
        boolean flag = true;
        while (flag) {
            System.out.print(message + ": ");
            String resultInput = input.nextLine();
            if (isDouble(resultInput)){
                doub = Double.parseDouble(resultInput);
                flag = false;
            } else {
                System.out.println(error);
            }
        }
        return doub;
    }

    /**
     * Method that accepts values from the user and checks if the value is integer.
     * @param message what the user should enter
     * @param input an instance of the class for entering information
     * @param error message if input is not Int
     * @return integer from user
     */
    public int consoleInt(String message, Scanner input, String error) {
        int integer = 0;
        boolean flag = true;
        while (flag) {
            System.out.print(message + ": ");
            String resultInput = input.nextLine();
            if (isInt(resultInput)){
                integer = Integer.parseInt(resultInput);
                flag = false;
            } else {
                System.out.println(error);
            }
        }
        return integer;
    }

    /**
     * Generates a random floating point number.
     * @return random price
     */
    public double randomPrice(){
        return (double) Math.round((Math.random()*10001) * 100) / 100;
    }
}
