package com.company.app.controller.command.template;

import com.company.app.entity.Film;
import com.company.app.service.CatalogService;

import java.util.Random;
import java.util.Scanner;

/**
 * Creation and cataloging Film.
 */
public class FilmProduct extends AbstractProduct {
    public FilmProduct(CatalogService catalogService, MethodsCreation howCreate) {
        super(catalogService, howCreate);
    }

    /**
     * {@link AbstractProduct#randomProduct()}
     */
    @Override
    public void randomProduct() {
        catalogService.addInCatalog(new Film("author " + new Random().nextInt(10001),
                "genre " + new Random().nextInt(10001),
                "country " + new Random().nextInt(10001)),
                randomPrice());
    }

    /**
     * {@link AbstractProduct#consoleProduct()}
     */
    @Override
    public void consoleProduct() {
        Scanner input = new Scanner(System.in);
        Film film = new Film();

        System.out.print("Author: ");
        film.setAuthor(input.nextLine());
        System.out.print("Genre: ");
        film.setGenre(input.nextLine());
        System.out.print("Country: ");
        film.setCountry(input.nextLine());
        catalogService.addInCatalog(film, consoleDouble("Price", input, doubleError));
    }
}
