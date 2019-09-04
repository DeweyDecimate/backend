package com.DeweyDecimate.Omnibus.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String title;
    String author;
    String description;
    String bookImg;

    Boolean isCurrent;

    @ManyToOne
    BookClub bookClubId;

    public Book(String title, String author, String description, String bookImg, BookClub bookClubId, Boolean isCurrent) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.bookImg = bookImg;
        this.bookClubId = bookClubId;
        this.isCurrent = isCurrent;
    }

    public Book(){}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getBookImg() {
        return bookImg;
    }

    public BookClub getBookClubId() {
        return bookClubId;
    }

    public Boolean getCurrent() {
        return isCurrent;
    }

    public void setCurrent(Boolean current) {
        isCurrent = current;
    }

}
