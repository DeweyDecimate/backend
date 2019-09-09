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
        this.randomId = this.generateRandomId(clubName);
    }

    public BookClub(){}

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
