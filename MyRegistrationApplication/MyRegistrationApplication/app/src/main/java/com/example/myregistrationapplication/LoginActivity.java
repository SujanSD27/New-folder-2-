package com.example.myregistrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
        EditText username_txt,password_txt;
        Button login_btn;
        int counter = 2;
        String registeredUsername, registeredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_txt = (EditText) findViewById(R.id.edtxt_username);
        password_txt = (EditText) findViewById(R.id.edtxt_password);

        login_btn = (Button) findViewById(R.id.btn_login);

        registeredUsername = getIntent().getStringExtra("username").toString();
        registeredPassword = getIntent().getStringExtra("password").toString();
    }
    public void login(View view)
    {
        String enteredUsername = username_txt.getText().toString().trim();
        String enteredPassword = password_txt.getText().toString().trim();

        if(!enteredUsername.equals(registeredUsername) || !enteredPassword.equals(registeredPassword)){
            Toast.makeText(this, "Invalid Username or Password",Toast.LENGTH_LONG).show();
            counter--;
            if(counter == 0){
                Toast.makeText(this,"@ Unsuccessful login attempts", Toast.LENGTH_LONG).show();
                login_btn.setEnabled(false);
            }
        }
        else {
            Intent intent = new Intent(this, LoginSuccessfulActivity.class);
            startActivity(intent);
        }
    }
}