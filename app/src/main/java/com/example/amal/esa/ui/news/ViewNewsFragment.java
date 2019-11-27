package com.example.amal.esa.ui.news;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amal.esa.R;


public class ViewNewsFragment extends Fragment {

    private ViewNewsViewModel mViewModel;

    public static ViewNewsFragment newInstance() {
        return new ViewNewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmetn_view_news, container, false);

        TextView tTitle = (TextView) rootView.findViewById(R.id.title);
        TextView dDecription = (TextView) rootView.findViewById(R.id.description);

        String title = getArguments().getString("title");
        String description = getArguments().getString("description");
        tTitle.setText(title);
        dDecription.setText(description);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewNewsViewModel.class);
        // TODO: Use the ViewModel
    }

}
