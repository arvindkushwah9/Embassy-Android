package com.example.amal.esa.ui.admarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amal.esa.R;
import com.example.amal.esa.interfaces.CustomItemClickListener;
import com.example.amal.esa.ui.notification.Service;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MyViewHolder> {

    private List<AdMarket> moviesList;
    private Context mContext;
    CustomItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,description;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description=(TextView) view.findViewById(R.id.description);
            imageView=(ImageView) view.findViewById(R.id.s2);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(mContext, gson.class);
                  //  mContext.startActivity(intent);
                }
            });
        }
    }

    public MarketAdapter(Context mContext, List<AdMarket> moviesList, CustomItemClickListener listener) {
        this.moviesList = moviesList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_market, parent, false);
        final MyViewHolder mViewHolder = new MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getPosition());
            }
        });
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AdMarket movie = moviesList.get(position);
        holder.title.setText(movie.title);
        holder.description.setText(movie.description);

        Glide
                .with(mContext) // replace with 'this' if it's in activity
                .load(movie.image)
                .error(R.drawable.news_demo)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}