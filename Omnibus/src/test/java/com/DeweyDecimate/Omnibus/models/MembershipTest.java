package com.DeweyDecimate.Omnibus.models;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class MembershipTest {

    ApplicationUser testUser = new ApplicationUser("testUserName","testUserPassword","testFirstName","testLastName");
    BookClub testClub = new BookClub("clubDescription","clubName","clubImg");
    Date date = new Date(System.currentTimeMillis());
    Membership testGetUser = new Membership(testUser, testClub, date);

    @Test
    public void testGetApplicationUser() {

        assertEquals(testUser,testGetUser.getApplicationUser());
    }


    @Test
    public void testGetDateJoined() {
        assertEquals("class java.sql.Date",testGetUser.getDateJoined().getClass().toString());
    }
}