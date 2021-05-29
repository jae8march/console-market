package com.company.app.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class film with genre and country properties.
 * @author Mariia_Kirina
 * @version 1.1
 */
public class Film extends Frame implements Serializable {
    private static final long serialVersionUID = 1L;
    private String genre;
    private String country;

    /**
     * Constructor - creating a new object with no value.
     */
    public Film() {
    }

    /**
     * Constructor - creates a new object specifying the author, genre and country of the film.
     * @param author - Frame author field
     * @param genre - Film genre field
     * @param country - Film production country field
     */
    public Film(String author, String genre, String country) {
        super(author);
        this.genre = genre;
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        Film film = (Film) object;
        return Objects.equals(genre, film.genre) && Objects.equals(country, film.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genre, country);
    }

    @Override
    public String toString() {
        return "Film{" +
                "genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", author='" + getAuthor() + '\'' +
                '}';
    }
}
