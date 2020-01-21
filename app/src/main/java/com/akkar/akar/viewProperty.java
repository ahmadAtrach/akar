package com.akkar.akar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class viewProperty extends AppCompatActivity {

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);
        getSupportActionBar().hide();
        Intent propertyIntent = getIntent();
        populate(propertyIntent);
       // TextView textv = (TextView) findViewById(R.id.txtView_propertyNamr);
        //textv.setShadowLayer(30, 0, 0, Color.GREEN);

    }
    public void goBack(View v) {
        Intent intent=new Intent(this,homePageActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
    public void addToFavorite(View v) {

    }
    public void populate(Intent intent){

       Integer Id = (Integer) intent.getExtras().get("Id");
       String area = Double.toString(intent.getExtras().getDouble("Area"));
       String bathnum = Integer.toString(intent.getExtras().getInt("Bathnum"));
       String bednum = Integer.toString(intent.getExtras().getInt("Bednum"));
       String furniture = intent.getExtras().getString("Furn");
       String price = Integer.toString(intent.getExtras().getInt("Price"));
       String title = intent.getExtras().getString("Title");
       String type = intent.getExtras().get("Type").toString();
        String img_url = intent.getExtras().get("img").toString();
        TextView txtView_title = (TextView) findViewById(R.id.txtView_propertyName);
        TextView txtView_price = (TextView) findViewById(R.id.txtView_price);
        TextView txtView_furni = (TextView) findViewById(R.id.txtView_furniture);
        TextView txtView_bednum = (TextView) findViewById(R.id.txtView_bedroom);
        TextView txtView_bathnum = (TextView) findViewById(R.id.txtView_bathroom);
        TextView txtView_area = (TextView) findViewById(R.id.txtView_area);
        TextView txtView_type = (TextView) findViewById(R.id.txtView_propertyType);
        final ImageView img = (ImageView) findViewById(R.id.imageView_propertyImage);
        txtView_title.setText(title);
        txtView_type.setText(type);
        txtView_price.setText(price);
        txtView_area.setText(area);
        txtView_bathnum.setText(bathnum);
        txtView_bednum.setText(bednum);
        txtView_furni.setText(furniture);

        String imageRequestURL
                = "https://akarr.000webhostapp.com/images/"+img_url;
            requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest =
                new ImageRequest(
                        imageRequestURL,
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                img.setImageBitmap(response);
                                System.out.println("hello");
                            }
                        },
                        0,
                        0,
                        ImageView.ScaleType.CENTER,
                        null,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                );
        requestQueue.add(imageRequest);
        //Intent propertyIntent = getIntent();
    }
}
