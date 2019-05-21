package com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.model.ResultsItem;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    public static final String DATA_MOVIE = "datamovie";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<ResultsItem> data = new ArrayList<>();

    //constructor

    public MovieAdapter(Context context, List<ResultsItem> data) {
        this.context = context;
        this.data = data;
    }

    //menyambungkan layout item

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup,false);

        return new MyViewHolder(itemView);
    }

    //set data
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

        myViewHolder.tvJudul.setText(data.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.get(position).getPosterPath()).into(myViewHolder.ivPoster);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(context, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_MOVIE, Parcels.wrap(data.get(position)));
                pindah.putExtra(DATA_EXTRA, bundle);
                context.startActivity(pindah);

            }
        });



    }

    //jumlah data
    @Override
    public int getItemCount() {
        return data.size();
    }

    //mengenal komponen dalam item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        ImageView ivPoster;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            ivPoster = itemView.findViewById(R.id.iv_item_gambar);

        }
    }

    //extend class ini
}
