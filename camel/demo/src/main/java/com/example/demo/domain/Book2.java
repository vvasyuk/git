package com.example.demo.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Jopa on 10/31/2017.
 */
@XmlRootElement(name="book")
public class Book2 {
    private String author;
    private String title;
    private String genre;
    private String price;


    @XmlElement
    public void setAuthor(String author) {this.author = author;}
    public String getAuthor() {return author;}

    @XmlElement
    public void setTitle(String title) {this.title = title;}
    public String getTitle() {return title;}

    @XmlElement
    public void setGenre(String genre) {this.genre = genre;}
    public String getGenre() {return genre;}

    @XmlElement
    public void setPrice(String price) {this.price = price;}
    public String getPrice() {return price;}

    @Override
    public String toString() {
        return "Book{" + "author='" + author + '\'' + ", title='" + title + '\'' + ", genre='" + genre + '\'' + ", price='" + price + '\'' + '}';
    }
}
