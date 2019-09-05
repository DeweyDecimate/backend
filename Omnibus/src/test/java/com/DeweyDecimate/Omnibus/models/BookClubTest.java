package com.DeweyDecimate.Omnibus.models;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.DeweyDecimate.Omnibus.models.BookClub.generateRandomId;
import static org.junit.Assert.*;

public class BookClubTest {

    @Test
    public void testGetCurrentBook(){
        final BookClub bookClub = new BookClub();
        Book book = new Book("Cool Book", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        bookClub.setCurrentBook(book);
        assertEquals(bookClub.getCurrentBook().title, "Cool Book");
    }

    @Test
    public void testGetDiscussions(){
        BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        bookClub.discussions = new ArrayList<>();
        ClubDiscussion cd1 = new ClubDiscussion();
        ClubDiscussion cd2 = new ClubDiscussion();
        ClubDiscussion cd3 = new ClubDiscussion();

        bookClub.discussions.add(cd1);
        bookClub.discussions.add(cd2);
        bookClub.discussions.add(cd3);

        assertEquals(bookClub.getDiscussions().size(), 3);
    }

    @Test
    public void testAddBooks(){
        BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        bookClub.books = new HashSet<>();
        Book book1 = new Book("Cool Book1", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        Book book2 = new Book("Cool Book2", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        Book book3 = new Book("Cool Book3", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        bookClub.addBooks(book1);
        bookClub.addBooks(book2);
        bookClub.addBooks(book3);

        assertEquals(bookClub.books.size(), 3);
    }

    @Test
    public void testGetMemberships(){
        BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        bookClub.memberships = new HashSet<>();
        Membership m1 = new Membership();
        Membership m2 = new Membership();
        Membership m3 = new Membership();
        bookClub.memberships.add(m1);
        bookClub.memberships.add(m2);
        bookClub.memberships.add(m3);

        assertEquals(bookClub.getMemberships().size(), 3);
    }

    @Test
    public void testGetBooks(){
        BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        bookClub.books = new HashSet<>();
        Book book1 = new Book("Cool Book1", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        Book book2 = new Book("Cool Book2", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        Book book3 = new Book("Cool Book3", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        bookClub.addBooks(book1);
        bookClub.addBooks(book2);
        bookClub.addBooks(book3);
        assertEquals(bookClub.getBooks().size(), 3);
    }

    @Test
    public void testSetCurrentBook(){
        final BookClub bookClub = new BookClub();
        Book book = new Book("Cool Book", "Cool Author", "Cool Description", "Cool Picture", bookClub);
        bookClub.setCurrentBook(book);
        assertEquals(book, bookClub.currentBook);
    }

    @Test
    public void testCanGetDescription(){
        final BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        assertEquals(bookClub.getDescription(), "Cool Club");
    }

    @Test
    public void testCanGetClubName(){
        final BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        assertEquals(bookClub.getClubName(), "Cool Name");
    }

    @Test
    public void testCanGetClubImage(){
        final BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        assertEquals(bookClub.getClubImg(), "Cool Picture");
    }

    @Test
    public void testGetRandomId(){
        BookClub bookClub = new BookClub();
        bookClub.randomId = generateRandomId("Cool Club");
        assertNotNull(bookClub.getRandomId());
    }

    @Test
    public void testGenerateRandomId(){
        HashSet<String> setOfId = new HashSet<>();
        for(int i = 0; i < 100; i++){
            setOfId.add(generateRandomId("Cool Club"));
        }
        assertEquals(setOfId.size(), 100);
    }

    @Test
    public void testToString(){
        final BookClub bookClub = new BookClub("Cool Club", "Cool Name", "Cool Picture");
        assertTrue(bookClub.toString().equals("Cool Club Cool Name"));
    }
}