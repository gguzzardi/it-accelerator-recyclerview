package com.example.gguzzardi.it_accelerator_recyclerview;

import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserLoginDataTest {

    private UserLoginData mLoginData;

    @Before
    public void init() {
        mLoginData = new UserLoginData();
    }

    @Test
    public void validateFailsWithEmptyPassword() {
        mLoginData.setPassword("");
        mLoginData.setEmail("232323@dsd.com");
        Assert.assertTrue(!mLoginData.validate());
    }

    @Test
    public void validateFailsWithEmptyEmail() {
        mLoginData.setPassword("123");
        mLoginData.setEmail("");
        Assert.assertTrue(!mLoginData.validate());
    }

    @Test
    public void validatePassesWithNonEmptyPasswordAndEmail() {
        mLoginData.setPassword("3244433");
        mLoginData.setEmail("232323@dsd.com");
        Assert.assertTrue(mLoginData.validate());
    }
}
