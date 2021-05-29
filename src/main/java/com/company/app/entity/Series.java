package com.company.app.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class series with seasons and episodes properties.
 * @author Mariia_Kirina
 * @version 1.1
 */
public class Series extends Film implements Serializable {
    private static final long serialVersionUID = 1L;
    private int seasons;
    private int episodes;

    /**
     * Constructor - creating a new object with no value.
     */
    public Series() {
    }

    /**
     * Constructor - creates a new object specifying number of seasons and episodes of the series.
     * @param seasons - {@link Series#seasons}
     * @param series - {@link Series#episodes}
     */
    public Series(int seasons, int series) {
        this.seasons = seasons;
        this.episodes = series;
    }

    /**
     * Constructor - creates a new object specifying author, genre, country,
     * number of seasons and episodes of the series.
     * @param author - Frame author field
     * @param genre - Film genre field
     * @param country - Film production country field
     * @param seasons - Series number of seasons field
     * @param episodes - Series number of episodes field
     */
    public Series(String author, String genre, String country, int seasons, int episodes) {
        super(author, genre, country);
        this.seasons = seasons;
        this.episodes = episodes;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
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
        Series series = (Series) object;
        return seasons == series.seasons && episodes == series.episodes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seasons, episodes);
    }

    @Override
    public String toString() {
        return "Series{" +
                "genre='" + getGenre() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", seasons='" + seasons + '\'' +
                ", episodes='" + episodes + '\'' +
                '}';
    }
}
