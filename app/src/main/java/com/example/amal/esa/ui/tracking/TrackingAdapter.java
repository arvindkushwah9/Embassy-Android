package com.example.amal.esa.ui.tracking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.R;

import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.MyViewHolder> {

    private List<Document> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView application, status, comment;

        public MyViewHolder(View view) {
            super(view);
            application = (TextView) view.findViewById(R.id.application);
            status = (TextView) view.findViewById(R.id.status);
            comment = (TextView) view.findViewById(R.id.comment);
        }
    }


    public TrackingAdapter(Context context,List<Document> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tracking, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Document document = moviesList.get(position);
        holder.application.setText(document.title);

        if (document.approved) {
            holder.status.setText("Approved");
        } else {
            holder.status.setText("Rejected");
        }

        holder.comment.setText("Img");
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}