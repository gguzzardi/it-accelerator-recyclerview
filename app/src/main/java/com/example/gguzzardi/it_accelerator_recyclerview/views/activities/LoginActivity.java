package com.example.gguzzardi.it_accelerator_recyclerview.views.activities;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gguzzardi.it_accelerator_recyclerview.R;
import com.example.gguzzardi.it_accelerator_recyclerview.model.UserLoginData;
import com.example.gguzzardi.it_accelerator_recyclerview.model.events.UserLoginEvent;
import com.example.gguzzardi.it_accelerator_recyclerview.presenters.LoginPresenter;
import com.example.gguzzardi.it_accelerator_recyclerview.views.interfaces.LoginView;
import com.mercadolibre.android.ui.widgets.MeliSnackbar;
import com.mercadolibre.android.ui.widgets.MeliSpinner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private static final String STATE_EMAIL = "state_email";
    private static final String STATE_PASSWORD = "state_password";

    private TextInputLayout mEmailInputLayout;
    private TextInputLayout mPasswordInputLayout;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private MeliSpinner mProgressBar;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailInputLayout = findViewById(R.id.inputLayout_email);
        mPasswordInputLayout = findViewById(R.id.inputLayout_password);
        mEmailEditText = findViewById(R.id.input_email);
        mPasswordEditText = findViewById(R.id.input_password);
        mLoginButton = findViewById(R.id.btn_login);
        mProgressBar = findViewById(R.id.pb_login);

        mLoginPresenter = new LoginPresenter(this, new UserLoginData());

        hideProgressBar();

        setupLoginButton();

        if (savedInstanceState != null) {
            mEmailEditText.setText(savedInstanceState.getString(STATE_EMAIL, ""));
            mPasswordEditText.setText(savedInstanceState.getString(STATE_PASSWORD, ""));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_EMAIL, mEmailEditText.getText().toString());
        outState.putString(STATE_PASSWORD, mPasswordEditText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void setupLoginButton() {
        mLoginButton.setOnClickListener(v -> {
            showProgressBar();
            attemptToLogin();
        });
    }

    private void openMainActivity() {
        Intent openMainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(openMainActivityIntent);
    }

    private void attemptToLogin() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mLoginPresenter.updateEmail(email);
        mLoginPresenter.updatePassword(password);
        mLoginPresenter.login();
    }

    private void showProgressBar() {
        mLoginButton.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mLoginButton.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoginSuccess() {
        openMainActivity();
    }

    @Override
    public void onLoginError() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.isEmpty()) {
            mEmailInputLayout.setError(String.format(getResources().getString(R.string.error_empty_field), "Email"));
        } else {
            mEmailInputLayout.setErrorEnabled(false);
        }
        if (password.isEmpty()) {
            mPasswordInputLayout.setError(String.format(getResources().getString(R.string.error_empty_field), "Password"));
        } else {
            mPasswordInputLayout.setErrorEnabled(false);
        }
        hideProgressBar();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserLogin(UserLoginEvent event) {
        Toast.makeText(this, "User has successfully login", Toast.LENGTH_SHORT).show();
    }
}
