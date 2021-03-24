package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, pass;
    Button singupBtn;
    TextView loginHref;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailField);
        pass = findViewById(R.id.passField);
        loginHref = findViewById(R.id.loginText);
        singupBtn = findViewById(R.id.singUpBtn);

        singupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailStr = email.getText().toString();
                String passStr = pass.getText().toString();

                if (emailStr.isEmpty() || passStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill out empty field", Toast.LENGTH_SHORT).show();
                } else if (!(emailStr.isEmpty() && passStr.isEmpty())) {
                    firebaseAuth.createUserWithEmailAndPassword(emailStr, passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Singup failed", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginHref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });
    }
}