package com.company.app.controller.command.template;

import com.company.app.entity.Frame;
import com.company.app.service.CatalogService;

import java.util.Random;
import java.util.Scanner;

/**
 * Creation and cataloging Frame.
 */
public class FrameProduct extends AbstractProduct {
    public FrameProduct(CatalogService catalogService, MethodsCreation howCreate) {
        super(catalogService, howCreate);
    }

    /**
     * {@link AbstractProduct#randomProduct()}
     */
    @Override
    public void randomProduct() {
        catalogService.addInCatalog(new Frame("author " + new Random().nextInt(10001)),
                randomPrice());
    }

    /**
     * {@link AbstractProduct#consoleProduct()}
     */
    @Override
    public void consoleProduct(){
        Scanner input = new Scanner(System.in);
        Frame frame = new Frame();

        System.out.print("Author: ");
        frame.setAuthor(input.nextLine());

        catalogService.addInCatalog(frame, consoleDouble("Price", input, doubleError));
    }
}
