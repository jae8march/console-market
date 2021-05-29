package com.company.app.controller.command.template;

import com.company.app.entity.Photo;
import com.company.app.service.CatalogService;

import java.util.Random;
import java.util.Scanner;

/**
 * Creation and cataloging Photo.
 */
public class PhotoProduct extends AbstractProduct {
    public PhotoProduct(CatalogService catalogService, MethodsCreation howCreate) {
        super(catalogService, howCreate);
    }

    /**
     * {@link AbstractProduct#randomProduct()}
     */
    @Override
    public void randomProduct(){
        catalogService.addInCatalog(new Photo("author " + new Random().nextInt(10001),
                new Random().nextInt(10001), new Random().nextInt(10001)), randomPrice());
    }

    /**
     * {@link AbstractProduct#consoleProduct()}
     */
    @Override
    public void consoleProduct(){
        Scanner input = new Scanner(System.in);
        Photo photo = new Photo();

        System.out.print("Author: ");
        photo.setAuthor(input.nextLine());

        photo.setHeight(consoleInt("Height", input, intError));
        photo.setWidth(consoleInt("Width", input, intError));

        catalogService.addInCatalog(photo, consoleDouble("Price", input, doubleError));
    }
}
