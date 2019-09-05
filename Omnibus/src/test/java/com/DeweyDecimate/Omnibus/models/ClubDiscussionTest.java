package com.DeweyDecimate.Omnibus.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClubDiscussionTest {

    ApplicationUser testUser = new ApplicationUser("testUserName","testUserPassword","testFirstName","testLastName");
    BookClub testClub = new BookClub("clubDescription","clubName","clubImg");
    ClubDiscussion testDiscussion = new ClubDiscussion("This is the content",testUser,testClub);

    @Test
    public void getTimeStamp() {
        assertEquals("class java.sql.Date",testDiscussion.getTimeStamp().getClass().toString());
    }

    @Test
    public void getContent() {
        assertEquals("This is the content",testDiscussion.getContent());
    }

    @Test
    public void getDiscussionUser() {
        assertEquals("testUserName (testFirstName testLastName)",testDiscussion.getDiscussionUser().toString());
    }

    @Test
    public void getBookClub() {
        assertEquals("clubDescription clubName",testDiscussion.getBookClub().toString());
    }
}