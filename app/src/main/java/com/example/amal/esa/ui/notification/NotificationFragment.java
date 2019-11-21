package com.example.amal.esa.ui.notification;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amal.esa.R;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.utility.SharedPrefManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {

    private NotificationViewModel mViewModel;
    RecyclerView recyclerView;
    public static NotificationFragment newInstance() {
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


         View root= inflater.inflate(R.layout.fragment_notification, container, false);
         recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getNotificationData();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NotificationViewModel.class);
        // TODO: Use the ViewModel
    }


    private void getNotificationData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        SharedPrefManager sharedPrefManager = new SharedPrefManager();
        String token = sharedPrefManager.getUserDetail(getActivity()).token;
        System.out.println("=====token=====" + token);
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Token " + token);

        System.out.println("==========" + map);

        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(getActivity());
        Call<GetNotification> mService = mApiService.getNotification(map);
        mService.enqueue(new Callback<GetNotification>() {
            @Override
            public void onResponse(Call<GetNotification> call, Response<GetNotification> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    GetNotification notification = response.body();
                    NotificationAdapter mAdapter = new NotificationAdapter(getActivity(), notification.getNotification);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    try {
                        System.out.println("============" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetNotification> call, Throwable t) {
                String message = t.getMessage();
                progressDialog.dismiss();
                Log.d("failure", message);
                // System.out.println("===========fail========" + message);
                call.cancel();
                //mLoding_Spinner.setVisibility(View.GONE);
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
