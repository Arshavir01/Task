package com.example.user.task;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static TextView yearTV, nameTV, rateTV;
    public static ImageView toolbarImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //get data from Rest server
        callForData();

    }

    public void init(){
        toolbarImage= (ImageView)findViewById(R.id.toolbarImageID);
        yearTV = (TextView)findViewById(R.id.yearId);
        nameTV = (TextView)findViewById(R.id.nameId);
        rateTV = (TextView)findViewById(R.id.rateId);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewId);

        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(1,1);
        recyclerView.setLayoutManager(sglm);
    }

    //get data from JsonParsing class
    public void callForData(){
        if(isNetworkAvailable()){
            JsonParsing parsing = new JsonParsing(this, recyclerView);
            parsing.loadRecyclerViewData();
        }else{
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
            Toast.makeText(this, "Turn On Internet", Toast.LENGTH_LONG).show();
        }

    }

    //for checking Internet connection
    boolean isNetworkAvailable(){
        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else return false;
    }


    //Post button
    public void postClick(View view) {
        Toast.makeText(this, "POST", Toast.LENGTH_SHORT).show();
    }

    //Likes button
    public void likesClick(View view) {
        Toast.makeText(this, "LIKES", Toast.LENGTH_SHORT).show();
    }

    //WishList button
    public void wishListClick(View view) {
        Toast.makeText(this, "WISHLIST", Toast.LENGTH_SHORT).show();
    }
}
