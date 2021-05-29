package com.company.app.controller.command.template;

import com.company.app.entity.Series;
import com.company.app.service.CatalogService;
import java.util.Random;
import java.util.Scanner;

/**
 * Creation and cataloging Series.
 */
public class SeriesProduct extends AbstractProduct {
    public SeriesProduct(CatalogService catalogService, MethodsCreation howCreate) {
        super(catalogService, howCreate);
    }

    /**
     * {@link AbstractProduct#randomProduct()}
     */
    @Override
    public void randomProduct() {
        catalogService.addInCatalog(new Series("author " + new Random().nextInt(10001),
                "genre " + new Random().nextInt(10001),
                "country " + new Random().nextInt(10001),
                new Random().nextInt(10001), new Random().nextInt(10001)), randomPrice());
    }

    /**
     * {@link AbstractProduct#consoleProduct()}
     */
    @Override
    public void consoleProduct() {
        Scanner input = new Scanner(System.in);
        Series series = new Series();

        System.out.print("Author: ");
        series.setAuthor(input.nextLine());
        System.out.print("Genre: ");
        series.setGenre(input.nextLine());
        System.out.print("Country: ");
        series.setCountry(input.nextLine());

        series.setSeasons(consoleInt("Seasons", input, intError));
        series.setEpisodes(consoleInt("Episodes", input, intError));
        catalogService.addInCatalog(series, consoleDouble("Price", input, doubleError));
    }
}
