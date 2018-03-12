package com.ignite.domain;

import org.apache.ignite.cache.affinity.AffinityKey;

/**
 * Created by Jopa on 3/12/2018.
 */
public class BookAff {

    private AffinityKey libId;
    private String author;
    private String title;
    private String genre;
    private String price;

    public BookAff(AffinityKey libId, String author, String title) {
        this.libId = libId;
        this.author = author;
        this.title = title;
    }

    public AffinityKey getLibId() {
        return libId;
    }

    public void setLibId(AffinityKey libId) {
        this.libId = libId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookAff{" +
                "libId=" + libId +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
