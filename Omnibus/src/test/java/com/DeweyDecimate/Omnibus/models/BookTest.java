package com.DeweyDecimate.Omnibus.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    BookClub linkedClub;
    Book testBook;

    @Before
    public void setTestBook() {
        linkedClub = new BookClub("Description", "Name", "img url");
        testBook = new Book("Book Title", "Author", "A book about lots of things.",
                "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/12/attachment_83090027.jpg?auto=format&q=60&fit=max&w=930",
                linkedClub);
    }

    @Test
    public void testGetTitle() {
        assertEquals("The book title should be returned",
                "Book Title", testBook.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals("The book author should be returned",
                "Author", testBook.getAuthor());
    }

    @Test
    public void testGetDescription() {
        assertEquals("The book description should be returned",
                "A book about lots of things.", testBook.getDescription());
    }

    @Test
    public void testGetBookImg() {
        assertEquals("The book image url should be returned",
                "https://99designs-blog.imgix.net/blog/wp-content/uploads/2017/12/attachment_83090027.jpg?auto=format&q=60&fit=max&w=930",
                testBook.getBookImg());
    }

    @Test
    public void testGetBookClubId() {
        assertEquals("The associated book club should be returned",
                linkedClub, testBook.getBookClubId());
    }
}