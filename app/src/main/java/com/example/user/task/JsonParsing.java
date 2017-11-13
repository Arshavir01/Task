package com.example.user.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12.11.2017.
 */

public class JsonParsing {
    private  Context context;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private final static String URL_DATA = "https://api.androidhive.info/json/movies.json"; //API link
    private List<ListItem> listItems;
    private String name = null;
    private String imageUrl = null;
    private String desc = null; //year of movie
    private String rate = null;

    public JsonParsing(Context context, RecyclerView rv) {
        this.context = context;
        this.rv = rv;
    }

    // Volley library
    public void loadRecyclerViewData(){
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        listItems = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        imageUrl = jsonObject1.getString("image");
                        name = jsonObject1.getString("title");
                        desc = jsonObject1.getString("releaseYear");
                        rate = jsonObject1.getString("rating");

                        ListItem item = new ListItem(name, desc, imageUrl, rate);
                        listItems.add(item);

                    }

                    // custom adapter
                    adapter = new DataAdapter(listItems, context);
                    rv.setAdapter(adapter);

                    //for Toolbar data and image
                    MainActivity.nameTV.setText(name);
                    MainActivity.yearTV.setText(desc);
                    MainActivity.rateTV.setText(rate);

                    Picasso.with(context)
                            .load(imageUrl)
                            .into(MainActivity.toolbarImage);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(context, (CharSequence) error, Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
