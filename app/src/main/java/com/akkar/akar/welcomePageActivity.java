package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class welcomePageActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn , signUp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
//        FirebaseAuth.getInstance().signOut();
        findViewById(R.id.sign_in).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
           Intent intent = new Intent(this,homePageActivity.class);
           startActivity(intent);
        }
    }

    private void signUp(){
        Intent intent=new Intent(welcomePageActivity.this,registerActivity.class);
        startActivity(intent);
    }
    private void signIn(){
        Intent intent=new Intent(welcomePageActivity.this,loginActivity.class);
        startActivity(intent);
    }
    public void onClick (View v){
        switch (v.getId()) {
            case R.id.sign_up:
                signUp();
                break;
            case R.id.sign_in:
                signIn();
                break;
        }
    }

}
