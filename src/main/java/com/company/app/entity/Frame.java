package com.company.app.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class frame with author properties.
 */
public class Frame implements Serializable {
    private static final long serialVersionUID = 1L;
    private String author;

    /**
     * Constructor - creating a new object with no value.
     */
    public Frame() {
    }

    /**
     * Constructor - creating a new object specifying of the author of the frame.
     * @param author - {@link Frame#author}
     */
    public Frame(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Frame frame = (Frame) object;
        return Objects.equals(author, frame.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "author='" + author + '\'' +
                '}';
    }
}
