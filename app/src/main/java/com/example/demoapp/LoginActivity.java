package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button btnLogin;
    TextView txtRegister;

    DbHandler dbHandler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.txtRegister);

        dbHandler = new DbHandler(LoginActivity.this);

        List<UserModel> allUser = dbHandler.getAllUser();


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password Empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length() < 4) {
                    Toast.makeText(LoginActivity.this, "Password is Weak", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(LoginActivity.this, "Enter Proper Email", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for (UserModel userModel : allUser){
                        Log.d("email",userModel.getEmail());
                        Log.d("password",userModel.getPassword());
                        if(userModel.getEmail().equals(email) && userModel.getPassword().equals(password)){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Invalid Email & Password", Toast.LENGTH_SHORT).show();
                        }
                    }

//                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}