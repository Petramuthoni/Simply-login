package com.example.simplelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TextInputLayout;

import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class login extends AppCompatActivity {
    RelativeLayout relativeLayout;
    AppCompatEditText Password,Email;
    TextInputLayout passwordLayout,emailLayout;
    Data sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteDatabase=new Data(this, login.class);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_login);
        relativeLayout.setOnClickListener(null);
        Email = (AppCompatEditText) findViewById(R.id.Emaillo_TextField);
        Password = (AppCompatEditText) findViewById(R.id.passwordl_TextField);
        emailLayout = (TextInputLayout) findViewById(R.id.Emaillo_TextInputLayout);
        passwordLayout = (TextInputLayout) findViewById(R.id.passwordl_TextInputLayout);
        Button login=(Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=Email.getText().toString();
                String pass=Password.getText().toString();
                Boolean checkemailPass=sqliteDatabase.emailpass(email,pass);
                if (checkemailPass==true) {

                    Toast.makeText(getApplicationContext(), "login successful", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "wrong password or email", Toast.LENGTH_SHORT).show();
                }



            }
        });
        passwordLayout.setCounterMaxLength(8);
        passwordLayout.setErrorEnabled(true);
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Email.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("please enter your email");
                } else {
                    emailLayout.setErrorEnabled(false);

                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (Email.getText().toString().isEmpty()) {
                    emailLayout.setErrorEnabled(true);
                    emailLayout.setError("please enter your email");

                } else {
                    emailLayout.setErrorEnabled(false);
                }

            }
        });

    }
}
