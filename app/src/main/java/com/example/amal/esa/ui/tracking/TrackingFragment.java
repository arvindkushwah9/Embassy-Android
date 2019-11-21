package com.example.amal.esa.ui.tracking;

import android.app.ProgressDialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.R;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.ui.profile.GetProfile;
import com.example.amal.esa.utility.SharedPrefManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackingFragment extends Fragment {

    private TrackingViewModel slideshowViewModel;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(TrackingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tracking, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getTrakingData();
        return root;
    }


    private void getTrakingData() {

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
        Call<Tracking> mService = mApiService.getTrakingList(map);
        mService.enqueue(new Callback<Tracking>() {
            @Override
            public void onResponse(Call<Tracking> call, Response<Tracking> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    Tracking tracking = response.body();
                    TrackingAdapter mAdapter = new TrackingAdapter(getActivity(), tracking.documents);
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
            public void onFailure(Call<Tracking> call, Throwable t) {
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