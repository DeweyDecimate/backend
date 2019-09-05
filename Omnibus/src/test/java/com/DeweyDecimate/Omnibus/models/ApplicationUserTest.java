package com.DeweyDecimate.Omnibus.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationUserTest {
    ApplicationUser testUser = new ApplicationUser("testUserName","testUserPassword","testFirstName","testLastName");


    @Test
    public void getUsername() {
        assertEquals("testUserName",testUser.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("testUserPassword",testUser.getPassword());
    }

    @Test
    public void getFirstName() {
        assertEquals("testFirstName",testUser.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals("testLastName",testUser.getLastName());
    }

    @Test
    public void getUserImg() {
        assertEquals("/default-avatar.png",testUser.getUserImg());
    }

    @Test
    public void toString1() {
        String expected = "testUserName (testFirstName testLastName)";
        String actual = testUser.toString();
        assertEquals(expected,actual);
    }
    @Test
    public void setImg(){
        testUser.setUserImg("/newUrl");
        String expected = "/newUrl";
        String actual = testUser.getUserImg();
        assertEquals(expected,actual);
    }
}