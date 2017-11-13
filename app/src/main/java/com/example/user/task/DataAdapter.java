package com.example.user.task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 12.11.2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<ListItem> listItems;
    private Context context;
    private ArrayList<Integer> counterList = new ArrayList<>(); // arrayList counter for onClick event

    public DataAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, final int position) {
        final ListItem listItem = listItems.get(position);
        Picasso.with(context)
                .load(listItem.getImageURL())
                .into(holder.imageView);

        //keep arrayList counter for onClick event
        for(int i = 0; i < position; i++){
            counterList.add(1);
        }

        // click on image
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // every image has its own counter in arrayList
                    int oldV = counterList.get(position);
                    int newV = oldV + 1;
                    counterList.set(position, newV);

                    ImageView imageView = (ImageView)v.findViewById(R.id.imageViewId);
                    android.view.ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                    // if image counter is even then zoom image else zoom out.
                    if(counterList.get(position) % 2 == 0){
                        layoutParams.width = 700;
                        layoutParams.height = 700;
                        imageView.setLayoutParams(layoutParams);
                    } else{
                        layoutParams.width = 185;
                        layoutParams.height = 200;
                        imageView.setLayoutParams(layoutParams);
                    }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.imageViewId);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linerLayoutId);


        }
    }
}
