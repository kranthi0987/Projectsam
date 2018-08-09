package com.sanjay.projectsam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sanjay.projectsam.R;
import com.sanjay.projectsam.model.Row;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder> {

    Context context;
    private List<Row> listrow;


    public RecyclerAdapter(List<Row> listrow, FragmentActivity activity) {
        this.listrow = listrow;
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
        holder.title.setText(product.getTitle());
        holder.description.setText(product.getDescription());
        Glide.with(context).load(product.getImageHref()).apply(RequestOptions.centerCropTransform()).into(holder.icon);
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
