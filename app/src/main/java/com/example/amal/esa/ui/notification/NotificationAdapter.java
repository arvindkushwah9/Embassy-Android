package com.example.amal.esa.ui.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private List<Notification> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView application;

        public MyViewHolder(View view) {
            super(view);
            application = (TextView) view.findViewById(R.id.title);
        }
    }


    public NotificationAdapter(Context context, List<Notification> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notification, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notification document = moviesList.get(position);
        holder.application.setText(document.title);


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}