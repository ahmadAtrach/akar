package com.akkar.akar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class welcomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        getSupportActionBar().hide();
    }
}
