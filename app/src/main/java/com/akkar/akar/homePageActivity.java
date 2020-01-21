package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class homePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
{
    TextView title;
    Intent addProperty;
    private ArrayList<property> propertiesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        title = (TextView) findViewById(R.id.pagetitle);
        title.setText(getResources().getString(R.string.title_home));
        addProperty =  new Intent(this, addProportyActivity.class);
          this.propertiesArrayList = new ArrayList<property>();
         final ListView listViewproperties = (ListView) findViewById(R.id.properties);
          RequestQueue requestQueue = Volley.newRequestQueue(this);
          String propertyRequestURL = "https://akarr.000webhostapp.com/properties.php";
            JsonArrayRequest propertyJSONRequest = new JsonArrayRequest(
                    propertyRequestURL,
                   new Response.Listener<JSONArray>() {
                        @Override
                    public void onResponse(JSONArray response) {
                        // System.out.println(response);
                        for(int i=0; i<response.length(); i++){
                            try {
                                JSONObject propertyJSONObject = response.getJSONObject(i);
                                property properties= new property(
                                        propertyJSONObject.getInt("id"),
                                        propertyJSONObject.getString("title"),
                                        propertyJSONObject.getInt("price"),
                                        propertyJSONObject.getString("type"),
                                        propertyJSONObject.getInt("bedrooms_nb"),
                                        propertyJSONObject.getInt("bathrooms_nb"),
                                        propertyJSONObject.getString("furnishings"),
                                        propertyJSONObject.getDouble("area"),
                                        propertyJSONObject.getString("images_url"),
                                        propertyJSONObject.getString("user_id")
                                );
                               propertiesArrayList.add(properties);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter propertyAdapter =
                        new ArrayAdapter(homePageActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        propertiesArrayList);
                        listViewproperties.setAdapter(propertyAdapter);
                        AdapterView.OnItemClickListener itemClickListener
                                = new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent drinkIntent = new Intent(homePageActivity.this,
                                        homePageActivity.class);
                                drinkIntent.putExtra("Id", propertiesArrayList.get(i).getId());
                                startActivity(drinkIntent);
                            }
                        };
                        listViewproperties.setOnItemClickListener(itemClickListener);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(propertyJSONRequest);
    }
    public void addPropertyClick(View v){ addProperty.putExtra("id",0);startActivity(addProperty); }


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
