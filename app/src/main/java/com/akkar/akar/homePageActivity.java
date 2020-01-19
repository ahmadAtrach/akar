package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class homePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    TextView title;
    Intent addProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.title_home));
        addProperty =  new Intent(this, addProportyActivity.class);

    }
    public void addPropertyClick(View v){
        startActivity(addProperty);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                title.setText(getResources().getString(R.string.title_home));
                return true;
            case R.id.navigation_profile:
                title.setText(getResources().getString(R.string.title_profile));
                return true;
            case R.id.navigation_search:
                title.setText(getResources().getString(R.string.title_search));
                return true;
        }
        return false;
    }
}
