package com.example.amal.esa.ui.services;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amal.esa.DashboardActivity;
import com.example.amal.esa.LoginActivity;
import com.example.amal.esa.R;
import com.example.amal.esa.model.LoginRequest;
import com.example.amal.esa.model.LoginResponse;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.utility.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddServiceFragment extends Fragment {

    private AddViewModel mViewModel;

    private Button mAddService;
    private TextInputEditText mTitle,mDescription;
    public static AddServiceFragment newInstance() {
        return new AddServiceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.add_fragment, container, false);

        mTitle = (TextInputEditText) rootview.findViewById(R.id.title);
        mDescription = (TextInputEditText) rootview.findViewById(R.id.description);
        mAddService=(Button)rootview.findViewById(R.id.add_service);
        mAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mTitle.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Please Enter Title",Toast.LENGTH_SHORT).show();
                }
                else if(mDescription.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"Please Enter Description",Toast.LENGTH_SHORT).show();

                }
                else {
                    // Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    // startActivity(intent);
                    addService(mTitle.getText().toString(),mDescription.getText().toString());
                }
                // validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        // TODO: Use the ViewModel
    }



    private void addService(String title, String description) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        AddService loginRequest = new AddService();
        loginRequest.title = title;
        loginRequest.description = description;

        SharedPrefManager sharedPrefManager1 = new SharedPrefManager();
        String token = sharedPrefManager1.getUserDetail(getActivity()).token;
        System.out.println("=====token=====" + token);
        Map<String, String> map1 = new HashMap<>();
        map1.put("Authorization", "Token " + token);
        map1.put("Content-Type","application/json");

        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(getActivity());
        Call<LoginResponse> mService = mApiService.postService(loginRequest,map1);
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);

               mTitle.setText("");
               mDescription.setText("");
                LoginResponse mLoginObject = response.body();

                // System.out.println("===========pass========" + mLoginObject);

                if (response.isSuccessful()) {

                    Toast.makeText(getActivity(), "Add Service Successfully", Toast.LENGTH_SHORT).show();
                    // if (mLoginObject.StatusCode.equals("NP001")) {
                    mTitle.setText("");
                    mDescription.setText("");
                    //  Toast.makeText(LoginActivity.this, mLoginObject.StatusDesc, Toast.LENGTH_SHORT).show();
                    // }

                } else {
                    try {
                        Log.e("unSuccess",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Toast.makeText(mContext, getResources().getString(R.string.invalid_user_name_password), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
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
