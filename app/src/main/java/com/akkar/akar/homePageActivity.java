package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class homePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        homeFragment.OnFragmentInteractionListener,searchFragment.OnFragmentInteractionListener,
        profileFragment.OnFragmentInteractionListener

{
    Fragment homePage = new homeFragment();
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
        loadFragment(homePage);

    }
    public void addPropertyClick(View v){
        startActivity(addProperty);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                title.setText(getResources().getString(R.string.title_home));
                loadFragment(homePage);
                return true;
            case R.id.navigation_profile:
                title.setText(getResources().getString(R.string.title_profile));
                loadFragment(new profileFragment());
                return true;
            case R.id.navigation_search:
                title.setText(getResources().getString(R.string.title_search));
                loadFragment(new searchFragment());
                return true;
        }
        return false;
    }
        public void loadFragment(Fragment fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameContainer, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    public void onContentFragmentInteraction(String string) {
        // Do different stuff
    }


    @Override
    public void OnFragmentInteractionListener(String string) {

    }

    @Override
    public void onContentFragmentInteraction2(String string) {

    }
}
