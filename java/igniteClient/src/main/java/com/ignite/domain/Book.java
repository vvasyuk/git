package com.ignite.domain;

import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.affinity.AffinityKeyMapped;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    @AffinityKeyMapped
    private String libId;
    private String author;
    private String title;
    private String genre;
    private String price;

    public String getLibId() {return libId;}
    public void setLibId(String libId) {this.libId = libId;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getGenre() {return genre;}
    public void setGenre(String genre) {this.genre = genre;}
    public String getPrice() {return price;}
    public void setPrice(String price) {this.price = price;}

    public Book(String libId,String author, String title) {
        this.libId=libId;
        this.author=author;
        this.title=title;
    }

    @Override
    public String toString() {
        return "Book{" + "author='" + author + '\'' + ", title='" + title + '\'' + ", genre='" + genre + '\'' + ", price='" + price + '\'' + '}';
    }
}
