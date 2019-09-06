package com.DeweyDecimate.Omnibus.models;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
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

    @Column(unique = true)
    String clubName;
    String clubImg;

    @Column(unique = true)
    String randomId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bookClub")
    List<ClubDiscussion> discussions;

    @OneToOne
    Book currentBook;

    public BookClub(String description, String clubName, String clubImg) {
        this.description = description;
        this.clubName = clubName;
        this.clubImg = clubImg;
        // since generateRandomId is a static method, you generally want to avoid
        // using this (or any instance) to access it.
        this.randomId = generateRandomId(clubName);
    }

    public BookClub(){}

    // Thank you for citing your source here!!
//  https://www.baeldung.com/java-random-string
    public static String generateRandomId(String clubName){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        // I like that you made the random id start with the club name.
        // The last thing you probably want to do at this point is make sure you
        // don't have a collision with any other club already in your database!
        // That way you can avoid the error when you try to insert it.
        // Or at least, you'd need that at Amazon scale.
        return clubName.replaceAll("[\\W]", "") + generatedString;
    }
    public List<ClubDiscussion> getDiscussions() {
        return discussions;
    }

    public void addBooks(Book book) {
        this.books.add(book);
    }

    public long getId() {
        return id;
    }

    public Set<Membership> getMemberships() {
        return memberships;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
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

    public String getRandomId() {
        return randomId;
    }

    public String toString() {
        return String.format("%s %s", this.description, this.clubName);
    }
}
