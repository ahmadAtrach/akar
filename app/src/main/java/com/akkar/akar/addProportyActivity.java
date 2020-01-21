package com.akkar.akar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

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
    private int Id;
    private int a;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proporty);
        getSupportActionBar().hide();
        b= findViewById(R.id.addEdit);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        user_id = currentFirebaseUser.getUid();
        title = (EditText) findViewById(R.id.titleeditText);
        price = (EditText) findViewById(R.id.price);
        furni = (EditText) findViewById(R.id.furnishing);
        bednum = (EditText) findViewById(R.id.bedroom_nb);
        bathnum = (EditText) findViewById(R.id.bathrooms_nb);
        area = (EditText) findViewById(R.id.area);
        requestQueue = Volley.newRequestQueue(this);
        spinner = (Spinner) findViewById(R.id.spinner);
        Intent propertyIntent = getIntent();
        Id = (Integer) propertyIntent.getExtras().get("Id");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
        // Apply the adapter to the spinner

        spinner.setAdapter(adapter);
        if (Id != 0) {
            b.setText("Edit");
            title.setText(propertyIntent.getExtras().getString("Title"));
            switch (propertyIntent.getExtras().get("Type").toString()) {
                case ("Apartment"):
                    a = 0;
                    break;
                case ("Land"):
                    a = 1;
                    break;
                case ("Full floor"):
                    a = 2;
                    break;
            }
            spinner.setSelection(a);
            price.setText(Integer.toString(propertyIntent.getExtras().getInt("Price")));
            furni.setText(propertyIntent.getExtras().getString("Furn"));
            bednum.setText(Integer.toString(propertyIntent.getExtras().getInt("Bednum")));
            bathnum.setText(Integer.toString(propertyIntent.getExtras().getInt("Bathnum")));
            String stringArea = Double.toString(propertyIntent.getExtras().getDouble("Area"));
            area.setText(stringArea);

    }
        else{
            b.setText("Add");
        }
    }

    public void add(View v){
        final String title;
        final String type;
        final int price;
        final String furni;
        final int bednum;
        final int bathnum;
        final double area;
        final String user_id;
        final String image;
        title = this.title.getText().toString();
        price = Integer.valueOf(this.price.getText().toString());
        furni = this.furni.getText().toString();
        bednum = Integer.valueOf(this.bednum.getText().toString());
        bathnum = Integer.valueOf(this.bathnum.getText().toString());
        area = Double.valueOf(this.area.getText().toString());
        type = spinner.getSelectedItem().toString();
        user_id = this.user_id;
        int id=this.Id;
        image= this.imagurl;
        if(title.isEmpty())
                {
            Toast.makeText(this,"Please fill all the inputs",Toast.LENGTH_SHORT).show();
        } else {
            if (Id != 0) {
                String url = "https://akarr.000webhostapp.com/editporperties.php";
                StringRequest updateRequest =
                        new StringRequest(
                                Request.Method.POST,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println("Response: " + response);
                                        if (response.equals("1")) {
                                            Toast.makeText(addProportyActivity.this, "Edited successfully", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(addProportyActivity.this, homePageActivity.class));
                                        } else {
                                            Toast.makeText(addProportyActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                }) {
                            @Override
                            public byte[] getBody() throws AuthFailureError {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id",Id);
                                    jsonObject.put("price", price);
                                    jsonObject.put("type", type);
                                    jsonObject.put("bedrooms_nb", bednum);
                                    jsonObject.put("bathrooms_nb", bathnum);
                                    jsonObject.put("furnishings", furni);
                                    jsonObject.put("area", area);
                                    jsonObject.put("user_id", user_id);
                                    jsonObject.put("title", title);
                                    jsonObject.put("images_url", image);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return jsonObject.toString().getBytes();
                            }
                        };
                requestQueue.add(updateRequest);
            } else {
                String url = "https://akarr.000webhostapp.com/addproperty.php";
                StringRequest insertRequest =
                        new StringRequest(
                                Request.Method.POST,
                                url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println("Update response " + response);
                                        if (response.equals("1")) {
                                            Toast.makeText(addProportyActivity.this, "Edited successfully", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(addProportyActivity.this, homePageActivity.class));
                                        } else {
                                            Toast.makeText(addProportyActivity.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                }
                        ) {
                            @Override
                            public byte[] getBody() {
                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("price", price);
                                    jsonObject.put("type", type);
                                    jsonObject.put("bedrooms_nb", bednum);
                                    jsonObject.put("bathrooms_nb", bathnum);
                                    jsonObject.put("furnishings", furni);
                                    jsonObject.put("area", area);
                                    jsonObject.put("user_id", user_id);
                                    jsonObject.put("title", title);
                                    jsonObject.put("images_url", image);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return jsonObject.toString().getBytes();
                            }
                        };
                requestQueue.add(insertRequest);
            }

        }
    }
}

