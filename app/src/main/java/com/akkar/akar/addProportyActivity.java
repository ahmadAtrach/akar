package com.akkar.akar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class addProportyActivity extends AppCompatActivity {
    Spinner spinner;
    private RequestQueue requestQueue;
    private EditText title;
    private String type;
    private EditText price;
    private EditText furni;
    private EditText bednum;
    private EditText bathnum;
    private EditText area;
    private String user_id;
    private String imagurl = "";
    private FirebaseAuth mAuth;
    private TextView pagetitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proporty);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        user_id = currentFirebaseUser.getUid();
        title =(EditText)findViewById(R.id.titleeditText);
        price =(EditText) findViewById(R.id.price);
        furni = (EditText) findViewById(R.id.furnishing);
        bednum = (EditText) findViewById(R.id.bedroom_nb);
        bathnum = (EditText) findViewById(R.id.bathrooms_nb);
        area = (EditText) findViewById(R.id.area);
        requestQueue = Volley.newRequestQueue(this);
         spinner= (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    public void onclick(View v){
        type = spinner.getSelectedItem().toString();
    }

}
