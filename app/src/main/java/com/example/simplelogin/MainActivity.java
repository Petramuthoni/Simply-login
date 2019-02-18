package com.example.simplelogin;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import android.view.View;
import android.widget.Button;

import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    AppCompatEditText Firstname,Lastname,Password,Email,Phone;
    TextInputLayout firstLayout ,passwordLayout,lastLayout,emailLayout,phoneLayout;
    Data sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sqliteDatabase=new Data(this, login.class);
        relativeLayout=(RelativeLayout)findViewById(R.id.activity_registration);
        relativeLayout.setOnClickListener(null);
        Email=(AppCompatEditText)findViewById(R.id.Emailr_TextField);
        Firstname=(AppCompatEditText)findViewById(R.id.Firstname_TextField);
        Lastname=(AppCompatEditText)findViewById(R.id.Lasttname_TextField);
        Phone=(AppCompatEditText)findViewById(R.id.Phone_TextField);
        Password=(AppCompatEditText)findViewById(R.id.passwordr_TextField);
        emailLayout=(TextInputLayout)findViewById(R.id.Emailr_TextInputLayout);
        firstLayout=(TextInputLayout)findViewById(R.id.Firstname_TextInputLayout);
        lastLayout=(TextInputLayout)findViewById(R.id.Lastname_TextInputLayout);
        phoneLayout=(TextInputLayout)findViewById(R.id.Phone_TextInputLayout);
        passwordLayout=(TextInputLayout)findViewById(R.id.passwordr_TextInputLayout);
        Button submit=(Button)findViewById(R.id.btnSubmit);
        Button go=(Button)findViewById(R.id.btnGo);
        passwordLayout.setCounterEnabled(true);
        passwordLayout.setCounterMaxLength(8);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String First=Firstname.getText().toString();
                String Last=Lastname.getText().toString();
                String email=Email.getText().toString();
                String pass=Password.getText().toString();
                String phone=Phone.getText().toString();
                if (First.equals("")||Last.equals("")||email.equals("")||pass.equals("")||phone.equals("")){
                    Toast.makeText(getApplicationContext(),"fields are empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkEmail=sqliteDatabase.checkEmail(email);
                    if (checkEmail==true){
                        Boolean insert=sqliteDatabase.insert(First,Last,email,pass,phone);
                        if (insert==true){
                            Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                            //Intent intent= new Intent(MainActivity.this,registration.class);
                            //startActivity(intent);


                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Enter correct credentials",Toast.LENGTH_SHORT).show();


                    }
                }




            }
        });
    }

}




