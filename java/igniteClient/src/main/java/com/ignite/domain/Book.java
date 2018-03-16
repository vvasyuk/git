package com.ignite.domain;

import org.apache.ignite.cache.affinity.AffinityKey;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;
    private String libId;
    private String author;
    private String title;
    private AffinityKey key;

    public AffinityKey key(){
        if(this.key == null){
            this.key = new AffinityKey(id, libId);
        }
        return key;
    }

    public Book(String id, String libId, String author, String title) {
        this.id = id;
        this.libId = libId;
        this.author = author;
        this.title = title;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getLibId() {return libId;}
    public void setLibId(String libId) {this.libId = libId;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", libId='" + libId + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", key=" + key +
                '}';
    }
}
