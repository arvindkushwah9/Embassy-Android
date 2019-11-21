package com.example.amal.esa.ui.services;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.amal.esa.NotificationActivity;
import com.example.amal.esa.R;


public class ServicesFragment extends Fragment {

    private ServicesViewModel mViewModel;
    LinearLayout passRenewal;

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_services, container, false);

        passRenewal=rootView.findViewById(R.id.pass_reneval);
        passRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ServicesViewModel.class);
        // TODO: Use the ViewModel
    }

}
