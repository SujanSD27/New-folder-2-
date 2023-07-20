package com.example.myregistrationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText username_txt, password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_txt = (EditText)findViewById(R.id.edtxt_username);
        password_txt = (EditText)findViewById(R.id.edtxt_password);
    }
    public void signup(View view){
        String username = username_txt.getText().toString().trim();
        String password = password_txt.getText().toString().trim();

        if(!isValidPassword(password)){
            Toast.makeText(this, "password does not match rules", Toast.LENGTH_LONG).show();
            return;

        }
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        startActivity(intent);
    }
    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern specChar = Pattern.compile("^.*[^a-zA-z0-9].*$");
    private boolean isValidPassword(String pwd)
    {
        if(pwd.length() < 8)
            return false;
        if(!lowercase.matcher(pwd).matches())
            return false;
        if(!uppercase.matcher(pwd).matches())
            return false;
        if(!number.matcher(pwd).matches())
            return false;
        if(!specChar.matcher(pwd).matches())
            return false;
    return true;
    }
}