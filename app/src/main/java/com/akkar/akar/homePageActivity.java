package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class homePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView title;
    Intent addProperty;
    private ArrayList<property> propertiesArrayList;
    Button searchButton;
    EditText searchInput;
    private ListView listViewproperties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchInput =(EditText) findViewById(R.id.searchInput);
        searchButton.setVisibility(View.GONE);
        searchInput.setVisibility(View.GONE);
        getSupportActionBar().hide();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        title = (TextView) findViewById(R.id.pagetitle);
        title.setText(getResources().getString(R.string.title_home));
        addProperty = new Intent(this, addProportyActivity.class);
        this.propertiesArrayList = new ArrayList<property>();
        listViewproperties = (ListView) findViewById(R.id.properties);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String propertyRequestURL = "https://akarr.000webhostapp.com/properties.php";
        JsonArrayRequest propertyJSONRequest = new JsonArrayRequest(
                propertyRequestURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // System.out.println(response);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject propertyJSONObject = response.getJSONObject(i);
                                property properties = new property(
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
                                Intent propertyIntent = new Intent(homePageActivity.this,
                                addProportyActivity.class);
                                propertyIntent.putExtra("Id", propertiesArrayList.get(i).getId());
                                propertyIntent.putExtra("Type", propertiesArrayList.get(i).getType());
                                propertyIntent.putExtra("Area", (propertiesArrayList.get(i).getArea()));
                                propertyIntent.putExtra("Bathnum", propertiesArrayList.get(i).getBathrooms_nb());
                                propertyIntent.putExtra("Bednum", propertiesArrayList.get(i).getBedrooms_nb());
                                propertyIntent.putExtra("Furn", propertiesArrayList.get(i).getFurnishings());
                                propertyIntent.putExtra("img", propertiesArrayList.get(i).getImages_url());
                                propertyIntent.putExtra("Price", propertiesArrayList.get(i).getPrice());
                                propertyIntent.putExtra("Title", propertiesArrayList.get(i).getTitle());

                                startActivity(propertyIntent);
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

    public void addPropertyClick(View v){
        addProperty.putExtra("Id",0);
        startActivity(addProperty);
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                title.setText(getResources().getString(R.string.title_home));
                searchButton.setVisibility(View.GONE);
                searchInput.setVisibility(View.GONE);
                return true;
            case R.id.navigation_profile:
                title.setText(getResources().getString(R.string.title_profile));
                searchButton.setVisibility(View.GONE);
                searchInput.setVisibility(View.GONE);
                return true;
            case R.id.navigation_search:
                title.setText(getResources().getString(R.string.title_search));
                searchButton.setVisibility(View.VISIBLE);
                searchInput.setVisibility(View.VISIBLE);
                return true;
        }
        return false;
    }
    public void searchById(View view){
        ArrayList searched = new ArrayList();
        EditText editTextId = (EditText) findViewById(R.id.searchInput);
        if(editTextId.getText().toString().equals("")){
            Toast.makeText(this,"Please Enter the name", Toast.LENGTH_SHORT).show();
        }else{
            String name = (editTextId.getText().toString());
            for(property property: propertiesArrayList){
                System.out.println("/////////////////iteration/////////////");
                if(property.getTitle().equals(name)){
                    Toast.makeText(this, "one thing found", Toast.LENGTH_LONG).show();
                    searched.add(property);
                }
            }
            ArrayAdapter searchedpropertyAdapter =
                    new ArrayAdapter(homePageActivity.this,
                            android.R.layout.simple_list_item_1,
                            searched);
            this.listViewproperties.setAdapter(searchedpropertyAdapter);
            Toast.makeText(this, "No property have this name", Toast.LENGTH_LONG).show();
        }
    }

}
