package com.DeweyDecimate.Omnibus.models;


import javax.persistence.*;
import java.util.Set;

@Entity
public class BookClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bookClubId")
    Set<Membership> memberships;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bookClubId")
    Set<Book> books;

    String description;
    String clubName;
    String clubImg;

    @Column(unique = true)
    long randomId;



    public BookClub(Set<Membership> memberships, Set<Book> books, String description, String clubName, String clubImg, long randomId) {
        this.memberships = memberships;
        this.books = books;
        this.description = description;
        this.clubName = clubName;
        this.clubImg = clubImg;
        this.randomId = randomId;
    }

    public BookClub(){}



    // TODO: static method to generate a random id



    public long getId() {
        return id;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public String getDescription() {
        return description;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubImg() {
        return clubImg;
    }

    public long getRandomId() {
        return randomId;
    }
}
