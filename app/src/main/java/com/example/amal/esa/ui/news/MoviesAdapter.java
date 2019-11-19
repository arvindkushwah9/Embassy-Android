package com.example.amal.esa.ui.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Post> moviesList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, descripiton;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.card_title);
            descripiton = (TextView) view.findViewById(R.id.card_subtitle);
            //year = (TextView) view.findViewById(R.id.year);
            imageView = (ImageView) view.findViewById(R.id.image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(mContext, gson.class);
                  //  mContext.startActivity(intent);
                }
            });
        }
    }

    public MoviesAdapter(Context mContext, List<Post> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post movie = moviesList.get(position);
        holder.title.setText(movie.title);
        holder.descripiton.setText(movie.description);
      //  Glide.with(mContext).load(movie.getFlag()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}