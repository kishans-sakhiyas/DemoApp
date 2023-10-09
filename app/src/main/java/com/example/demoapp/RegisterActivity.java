package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.data.DbHandler;
import com.example.demoapp.model.UserModel;

import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtNumber, edtEmail, edtPassword;
    TextView txtLogin;
    Button btnRegister;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Date currentTime = Calendar.getInstance().getTime();

        dbHandler = new DbHandler(RegisterActivity.this);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtLogin = findViewById(R.id.txtLogin);
        btnRegister = findViewById(R.id.btnRegister);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String number = edtNumber.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Name Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (number.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Number Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Password Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 4) {
                    Toast.makeText(RegisterActivity.this, "Password is Weak", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(RegisterActivity.this, "Enter Proper Email", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    UserModel userModel = new UserModel(name, number, email, password, currentTime.toString());
                    dbHandler.addUser(userModel);
                    Toast.makeText(RegisterActivity.this, "Sucess "+currentTime.toString(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }
}