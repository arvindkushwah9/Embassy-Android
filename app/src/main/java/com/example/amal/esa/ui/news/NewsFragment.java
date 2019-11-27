package com.example.amal.esa.ui.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amal.esa.NotificationActivity;
import com.example.amal.esa.R;
import com.example.amal.esa.interfaces.CustomItemClickListener;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.utility.SharedPrefManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment  {

    private NewsViewModel toolsViewModel;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
       // final TextView textView = root.findViewById(R.id.text_tools);
     /*   toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });*/

        ArrayList<Movie>moviesList=new ArrayList<>();
        //moviesList.add(new Movie("Title","","",""));

        recyclerView = (RecyclerView) root.findViewById(R.id.rv1);

        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        fetchData();
        return root;
    }

    private void fetchData() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

  SharedPrefManager sharedPrefManager =new SharedPrefManager();
   String token= sharedPrefManager.getUserDetail(getActivity()).token;
   System.out.println("=====token====="+token);
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", "Token "+token);

        System.out.println("=========="+map);

        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(getActivity());
        Call<Movie> mService = mApiService.getNews(map);
        mService.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);



                 if(response.isSuccessful()) {
                     final Movie movieList = response.body();

                     MoviesAdapter mAdapter = new MoviesAdapter(getActivity(), movieList.posts, new CustomItemClickListener() {
                         @Override
                         public void onItemClick(View v, int position) {
                             //Toast.makeText(v.getContext(),""+position,Toast.LENGTH_SHORT).show();
                             Intent intent=new Intent(getActivity(), NotificationActivity.class);
                             intent.putExtra("title",movieList.posts.get(position).title);
                             intent.putExtra("description",movieList.posts.get(position).description);
                             intent.putExtra("key",1);
                             startActivity(intent);
                         }
                     });
                     recyclerView.setAdapter(mAdapter);
                     // System.out.println("===========pass========" + mLoginObject);
                 }
                 else{
                     try {
                         System.out.println("============"+response.errorBody().string());
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
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