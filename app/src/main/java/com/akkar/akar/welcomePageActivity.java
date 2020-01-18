package com.akkar.akar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomePageActivity extends AppCompatActivity implements View.OnClickListener {
    Button signIn , signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getSupportActionBar().hide();
        findViewById(R.id.sign_in).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
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
