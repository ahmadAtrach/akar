package com.akkar.akar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    Intent homepage;
    private FirebaseAuth mAuth;
    private EditText mail, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         homepage = new Intent(this,homePageActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        this.mail=(EditText) findViewById(R.id.mail);
        this.password=(EditText)findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        findViewById(R.id.button_sign_in).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.button_sign_in):
                sign_in();
                break;
        }
    }

    private void sign_in() {
        final String email = mail.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (email.isEmpty()) {
            mail.setError(getString(R.string.input_error_email));
            mail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mail.setError(getString(R.string.input_error_email_invalid));
            mail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError(getString(R.string.input_error_password));
            password.requestFocus();
            return;
        }
        if (password.length() < 8) {
            password.setError(getString(R.string.input_error_password_length));
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    startActivity(homepage);
                    Toast.makeText(loginActivity.this, getString(R.string.login_successful), Toast.LENGTH_LONG).show();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(loginActivity.this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
