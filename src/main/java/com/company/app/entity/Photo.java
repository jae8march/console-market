package com.company.app.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class photo with height and width properties.
 * @author Mariia_Kirina
 * @version 1.1
 */
public class Photo extends Frame implements Serializable {
    private static final long serialVersionUID = 1L;
    private int height;
    private int width;

    /**
     * Constructor - creating a new object with no value.
     */
    public Photo() {
    }

    /**
     * Constructor - creates a new object specifying the author, genre and country of the film.
     * @param author - Frame author field
     * @param height Photo height field
     * @param width Photo width field
     */
    public Photo(String author, int height, int width) {
        super(author);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Photo photo = (Photo) o;
        return height == photo.height && width == photo.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "height=" + height +
                ", width=" + width +
                ", author='" + getAuthor() + '\'' +
                '}';
    }
}
