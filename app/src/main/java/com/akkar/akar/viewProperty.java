package com.akkar.akar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class viewProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);
        getSupportActionBar().hide();
       // TextView textv = (TextView) findViewById(R.id.txtView_propertyNamr);
        //textv.setShadowLayer(30, 0, 0, Color.GREEN);

    }
    public void goBack(View v) {
        Intent intent=new Intent(this,homePageActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void addToFavorite(View v) {

    }
}
