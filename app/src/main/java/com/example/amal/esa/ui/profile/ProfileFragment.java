package com.example.amal.esa.ui.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.amal.esa.R;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.ui.news.Movie;
import com.example.amal.esa.ui.news.MoviesAdapter;
import com.example.amal.esa.utility.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private ProfileViewModel galleryViewModel;

    TextInputEditText name, firstName, LastName, email;
    Button mSave;
    int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        name = root.findViewById(R.id.name);
        firstName = root.findViewById(R.id.first_name);
        LastName = root.findViewById(R.id.last_name);
        email = root.findViewById(R.id.email);
        mSave = root.findViewById(R.id.save);
       /* final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        getProfileData();
        return root;
    }

    private void saveData() {
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

        RequestUpdateProfile requestUpdateProfile = new RequestUpdateProfile();
        requestUpdateProfile.id = id;
        requestUpdateProfile.first_name = firstName.getText().toString();
        requestUpdateProfile.last_name = LastName.getText().toString();
        requestUpdateProfile.username = name.getText().toString();

        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(getActivity());
        Call<UpdateProfile> mService = mApiService.updateProfile(map, requestUpdateProfile);
        mService.enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);

                //System.out.println("==========update response===" + response.body().toString());

                if (response.isSuccessful()) {

                    UpdateProfile getProfile = response.body();


                    name.setText(getProfile.username);
                    firstName.setText(getProfile.first_name);
                    LastName.setText(getProfile.last_name);
                    email.setText(getProfile.email);
                    Toast.makeText(getActivity(),"Your Profile Successfully Updated",Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        System.out.println("============" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
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


    private void getProfileData() {

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
        Call<GetProfile> mService = mApiService.getProfile(map);
        mService.enqueue(new Callback<GetProfile>() {
            @Override
            public void onResponse(Call<GetProfile> call, Response<GetProfile> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                if (response.isSuccessful()) {

                    GetProfile getProfile = response.body();
                    id = getProfile.profile.id;
                    name.setText(getProfile.profile.username);
                    firstName.setText(getProfile.profile.firstName);
                    LastName.setText(getProfile.profile.lastName);
                    email.setText(getProfile.profile.email);
                } else {
                    try {
                        System.out.println("============" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetProfile> call, Throwable t) {
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