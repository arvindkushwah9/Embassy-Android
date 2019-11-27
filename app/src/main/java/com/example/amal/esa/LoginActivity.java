package com.example.amal.esa;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amal.esa.model.LoginRequest;
import com.example.amal.esa.model.LoginResponse;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.utility.SharedPrefManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Invalid;
    private TextView mSignUp,mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPrefManager sharedPrefManager=new SharedPrefManager();
        if(sharedPrefManager.getUserDetail(LoginActivity.this)!=null){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        Name = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.btnLogin);
        Invalid = (TextView)findViewById(R.id.InvalEmailPassword);
        mSignUp=(TextView)findViewById(R.id.sign_up);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mForgotPassword=findViewById(R.id.textView);
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Name.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"Please Enter User Name",Toast.LENGTH_SHORT).show();
                }
                else if(Password.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this,"Please Enter Your Password ",Toast.LENGTH_SHORT).show();

                }
                else {
                   // Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                   // startActivity(intent);
                    processLogin(Name.getText().toString(),Password.getText().toString());
                }
               // validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }
    private void validate(String userName, String userPassword){
        if((userName.equals("")) && (userPassword.equals("password"))){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else
        {

            Invalid.setText("Invalid email/password ");
        }
    }



    private void processLogin(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.login = email;
        loginRequest.password = password;
        // System.out.println("========" + loginRequest);


        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(LoginActivity.this);
        Call<LoginResponse> mService = mApiService.login(loginRequest);
        mService.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                LoginResponse mLoginObject = response.body();

                // System.out.println("===========pass========" + mLoginObject);

                if (response.isSuccessful()) {
                   // if (mLoginObject.StatusCode.equals("NP001")) {
                        Log.e("Success", new Gson().toJson(response.body()));
                        SharedPrefManager sharedPrefManager = new SharedPrefManager();
                        sharedPrefManager.saveUserDeatail(LoginActivity.this, mLoginObject);
                        Intent mIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mIntent);
                        finish();
                   // } else {
                      //  Toast.makeText(LoginActivity.this, mLoginObject.StatusDesc, Toast.LENGTH_SHORT).show();
                   // }

                } else {
                    Log.e("unSuccess", new Gson().toJson(response.errorBody()));
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
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

}
