package com.example.gguzzardi.it_accelerator_recyclerview;

import com.example.gguzzardi.it_accelerator_recyclerview.model.User;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private User mMockedUser;

    private LoginPresenter mLoginPresenter;

    @Before
    public void initPresenter() {
        mLoginPresenter = new LoginPresenter(mMockedUser);
    }

    @Test
    public void updateEmailCallsUserSetEmailMethod() {
        String email = "prueba@mercadolibre.com";
        mLoginPresenter.updateEmail(email);
        verify(mMockedUser, times(1)).setEmail(email);
    }

    @Test
    public void updatePasswordCallsUserSetPasswordMethod() {
        String password = "prueba12345!";
        mLoginPresenter.updatePassword(password);
        verify(mMockedUser, times(1)).setPassword(password);
    }
}
