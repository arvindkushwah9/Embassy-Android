package com.example.amal.esa.ui.services;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.amal.esa.NotificationActivity;
import com.example.amal.esa.R;
import com.example.amal.esa.interfaces.CustomItemClickListener;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.ui.notification.GetNotification;
import com.example.amal.esa.ui.notification.NotificationAdapter;
import com.example.amal.esa.ui.notification.Service;
import com.example.amal.esa.utility.SharedPrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ServicesFragment extends Fragment {

    private ServicesViewModel mViewModel;
    LinearLayout passRenewal;
    RecyclerView recyclerView;

    public static ServicesFragment newInstance() {
        return new ServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_services, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.r1);
        RecyclerView.LayoutManager mLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        passRenewal = rootView.findViewById(R.id.pass_reneval);
        passRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NotificationActivity.class);
                intent.putExtra("key", 0);
                startActivity(intent);
            }
        });
        getService();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ServicesViewModel.class);
        // TODO: Use the ViewModel
    }


    private void getService() {

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
        Call<List<Service>> mService = mApiService.getServices(map);

        mService.enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    final List<Service> servicesList = response.body();
                    System.out.println("===Abc======" + servicesList);

                    List<Service> list = new ArrayList<>();
                    Service service = new Service();
                    service.title = "Add Service";
                    list.add(service);
                    servicesList.addAll(list);


                    ServiceAdapter mAdapter = new ServiceAdapter(getActivity(), servicesList, new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                            String title= servicesList.get(position).title;

                            switch (title){

                                case"Add Service":
                                    Intent intent=new Intent(getActivity(),NotificationActivity.class);
                                    intent.putExtra("key",2);
                                    startActivity(intent);

                                    break;

                                case"Pass Renewal":
                                    Intent intent1=new Intent(getActivity(),NotificationActivity.class);
                                    intent1.putExtra("key",0);
                                    startActivity(intent1);

                                    break;
                            }

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
            public void onFailure(Call<List<Service>> call, Throwable t) {
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
