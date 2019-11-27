package com.example.amal.esa.ui.admarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.NotificationActivity;
import com.example.amal.esa.R;
import com.example.amal.esa.interfaces.CustomItemClickListener;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.ui.notification.GetNotification;
import com.example.amal.esa.ui.notification.NotificationAdapter;
import com.example.amal.esa.ui.notification.Service;
import com.example.amal.esa.ui.services.ServiceAdapter;
import com.example.amal.esa.utility.SharedPrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdMarketFragment extends Fragment {

    private AdMarketViewModel shareViewModel;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(AdMarketViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ad_market, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //TextView tTitle = (TextView) root.findViewById(R.id.title);
       // TextView dDecription = (TextView) root.findViewById(R.id.description);


        getAdmarket();

        return root;
    }



    private void getAdmarket() {

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
        Call<List<AdMarket>> mService = mApiService.getAdMarket(map);

        mService.enqueue(new Callback<List<AdMarket>>() {
            @Override
            public void onResponse(Call<List<AdMarket>> call, Response<List<AdMarket>> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    final List<AdMarket> servicesList = response.body();
                    System.out.println("===Abc======" + servicesList);

                    MarketAdapter mAdapter = new MarketAdapter(getActivity(), servicesList, new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                            //String title= servicesList.get(position).title;


                        }
                    });
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
            public void onFailure(Call<List<AdMarket>> call, Throwable t) {
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