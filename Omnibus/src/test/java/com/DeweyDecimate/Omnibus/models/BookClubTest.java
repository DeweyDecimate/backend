package com.DeweyDecimate.Omnibus.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookClubTest {

    @Test
    public void testSetter_setsProperly() throws NoSuchFieldException, IllegalAccessException {
        //given
        final BookClub pojo = new BookClub();

        //when
        pojo.setValue("foo");

        //then
        final Field field = pojo.getClass().getDeclaredField("value");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(pojo), "foo");
    }
//    @Test
//    public void testGenerateRandomId() {
//        System.out.println(BookClub.generateRandomId());
//    }
}