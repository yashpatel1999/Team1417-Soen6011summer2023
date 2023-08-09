package com.application.careerserviceapplication.models;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserLoginTest {

    private UserLogin userLogin;

    @Before
    public void setUp() {
        userLogin = new UserLogin();
        userLogin.setUid("john");
        userLogin.setPassword("secretpassword");
        userLogin.setType("user");
        userLogin.setApproved("yes");
    }

    @Test
    public void testGetters() {
        assertEquals("john", userLogin.getUid());
        assertEquals("secretpassword", userLogin.getPassword());
        assertEquals("user", userLogin.getType());
        assertEquals("yes", userLogin.getApproved());
    }

    @Test
    public void testSetters() {
        userLogin.setUid("jane");
        userLogin.setPassword("newpassword");
        userLogin.setType("admin");
        userLogin.setApproved("no");

        assertEquals("jane", userLogin.getUid());
        assertEquals("newpassword", userLogin.getPassword());
        assertEquals("admin", userLogin.getType());
        assertEquals("no", userLogin.getApproved());
    }

    @Test
    public void testToString() {
        String expectedToString = "john  secretpassword  user";
        assertEquals(expectedToString, userLogin.toString());
    }
}

