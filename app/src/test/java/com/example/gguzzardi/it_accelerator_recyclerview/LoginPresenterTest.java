package com.example.gguzzardi.it_accelerator_recyclerview;

import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.LoginPresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private UserLoginData mMockedUser;

    @Mock
    private LoginView mView;

    private LoginPresenter mLoginPresenter;

    @Before
    public void initPresenter() {
        mLoginPresenter = new LoginPresenter(mView, mMockedUser);
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

    @Test
    public void loginCallsUserDataValidateMethod() {
        mLoginPresenter.login();
        verify(mMockedUser, times(1)).validate();
    }

    @Test
    public void loginCallsViewOnLoginSuccessWhenDataIsValid() {
        when(mMockedUser.validate()).thenReturn(true);
        mLoginPresenter.login();
        verify(mView, times(1)).onLoginSuccess();
    }

    @Test
    public void loginCallsViewOnLoginErrorWhenDataIsNotValid() {
        when(mMockedUser.validate()).thenReturn(false);
        mLoginPresenter.login();
        verify(mView, times(1)).onLoginError();
    }
}
