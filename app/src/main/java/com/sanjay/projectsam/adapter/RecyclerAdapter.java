package com.sanjay.projectsam.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.sanjay.projectsam.R;
import com.sanjay.projectsam.model.Row;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    private List<Row> listrow;
    ImageLoader imageLoader = ImageLoader.getInstance();

    public RecyclerAdapter(RequestManager listrow, List<Row> list) {
        this.listrow = list;

    }

    public void setlist(List<Row> list) {
        this.listrow = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem, parent, false);
        MyviewHolder myHolder = new MyviewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Row product = listrow.get(position);
        ImageSize targetSize = new ImageSize(400, 400);
        if (product.getTitle() != null) {
            holder.title.setText(product.getTitle());
        } else {
            holder.title.setText("NULL");
        }
        if (product.getDescription() != null) {
            holder.description.setText(product.getDescription());
        } else {
            holder.description.setText("Null");
        }
        if (product.getImageHref() != null) {
            imageLoader.displayImage(String.valueOf(product.getImageHref()), holder.icon, targetSize);
        } else {
            imageLoader.displayImage(String.valueOf(R.drawable.ic_launcher_background), holder.icon, targetSize);
        }

//        Glide.with(context).load(product.getImageHref()).apply(RequestOptions.centerCropTransform()).into(holder.icon);

    }


    @Override
    public int getItemCount() {
        System.out.println("itemcount" + listrow.size());
        return listrow.size();
    }


    public static class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, description;

        public MyviewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.listinimg);
            title = itemView.findViewById(R.id.listintext);
            description = itemView.findViewById(R.id.listindescription);
        }
    }
}
