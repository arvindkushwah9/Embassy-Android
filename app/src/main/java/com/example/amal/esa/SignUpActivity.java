package com.example.amal.esa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amal.esa.model.ErrorPoJo;
import com.example.amal.esa.model.LoginRequest;
import com.example.amal.esa.model.LoginResponse;
import com.example.amal.esa.model.RegiserResponse;
import com.example.amal.esa.model.RegisterRequest;
import com.example.amal.esa.network.ApiClient;
import com.example.amal.esa.network.ApiInterface;
import com.example.amal.esa.utility.SharedPrefManager;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        final TextInputEditText name = (TextInputEditText) findViewById(R.id.name);
        final TextInputEditText first_name = (TextInputEditText) findViewById(R.id.first_name);
        final TextInputEditText last_name = (TextInputEditText) findViewById(R.id.last_name);
        final TextInputEditText email = (TextInputEditText) findViewById(R.id.email);
        final TextInputEditText password = (TextInputEditText) findViewById(R.id.password);
        final TextInputEditText confirm_password = (TextInputEditText) findViewById(R.id.confirm_password);
        final TextInputEditText passport_number = (TextInputEditText) findViewById(R.id.passport_number);
        final TextInputEditText phone_number = (TextInputEditText) findViewById(R.id.phone_number);

        TextView mSignIn=(TextView)findViewById(R.id.sign_in);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button mSignUpBtn = (Button) findViewById(R.id.sign_up);
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter user name", Toast.LENGTH_SHORT).show();
                } else if (first_name.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter First name", Toast.LENGTH_SHORT).show();
                } else if (last_name.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Last name", Toast.LENGTH_SHORT).show();
                }
                else if (passport_number.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Passport Number;", Toast.LENGTH_SHORT).show();
                }
                else if (phone_number.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Phone Number;", Toast.LENGTH_SHORT).show();
                }
                else if (email.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (confirm_password.getText().toString().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!password.getText().toString().equals(confirm_password.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "Please Check Your Password", Toast.LENGTH_SHORT).show();

                } else {

                    processRegister(name.getText().toString(), first_name.getText().toString(), last_name.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),passport_number.getText().toString(),phone_number.getText().toString());

                }
            }
        });

    }


    private void processRegister(String userName, String firstName, String lastName, String email, String password,String passportNumber,String phoneNumber) {

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        RegisterRequest loginRequest = new RegisterRequest();
        loginRequest.username = userName;
        loginRequest.first_name = firstName;
        loginRequest.last_name = lastName;
        loginRequest.email = email;
        loginRequest.password = password;
        loginRequest.password_confirm = password;
        loginRequest.passport_number = passportNumber;
        loginRequest.phone_number = phoneNumber;
        System.out.println("====login request====" + loginRequest);


        //  mLoding_Spinner.setVisibility(View.VISIBLE);
        ApiInterface mApiService = ApiClient.getInterfaceService(SignUpActivity.this);
        Call<RegiserResponse> mService = mApiService.register(loginRequest);
        mService.enqueue(new Callback<RegiserResponse>() {
            @Override
            public void onResponse(Call<RegiserResponse> call, Response<RegiserResponse> response) {
                progressDialog.dismiss();
                // mLoding_Spinner.setVisibility(View.GONE);


                RegiserResponse mLoginObject = response.body();

                // System.out.println("===========pass========" + response.body());


                System.out.println("===========resposse========" + response.code());
                if (response.isSuccessful()) {
                    // if (mLoginObject.StatusCode.equals("NP001")) {
                    Log.e("Success", new Gson().toJson(response.body()));
                   /* SharedPrefManager sharedPrefManager = new SharedPrefManager();
                    sharedPrefManager.saveUserDeatail(SignUpActivity.this, mLoginObject);*/
                    Intent mIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mIntent);
                    finish();
                    Toast.makeText(SignUpActivity.this, "You have signed up successfully", Toast.LENGTH_SHORT).show();
                    // } else {
                    //  Toast.makeText(LoginActivity.this, mLoginObject.StatusDesc, Toast.LENGTH_SHORT).show();
                    // }

                } else {
                    // Log.e("unSuccess", new Gson().toJson(response.errorBody()));

                    try {
                        String errorBody = response.errorBody().string();

                        JSONObject jsonObject = new JSONObject(errorBody.trim());

                        System.out.println("==Json Object==========" + jsonObject);
                        Toast.makeText(SignUpActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                        jsonObject = jsonObject.getJSONObject("username");

                        jsonObject = jsonObject.getJSONObject("message");

                        Iterator<String> keys = jsonObject.keys();
                        String errors = "";
                        while (keys.hasNext()) {
                            String key = keys.next();
                            JSONArray arr = jsonObject.getJSONArray(key);
                            for (int i = 0; i < arr.length(); i++) {
                                errors += key + " : " + arr.getString(i) + "\n";
                            }
                        }
                        System.out.println("====vgg====" + errors);
                        //Common.errorLog("ERRORXV", errors);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                  /*  try {
                        Log.v("ErrorPoJo code 400",response.errorBody().string());
                        System.out.println("===========pass========" + response.errorBody().string());

                        Gson gson = new GsonBuilder().create();
                        ErrorPoJo mError;
                        try {
                            mError= gson.fromJson(response.errorBody().string(),ErrorPoJo.class);
                            System.out.println("Error=="+mError);
                            Toast.makeText(getApplicationContext(), ""+mError, Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            // handle failure to read error
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Toast.makeText(mContext, getResources().getString(R.string.invalid_user_name_password), Toast.LENGTH_SHORT).show();
                }*/
                }
            }

            @Override
            public void onFailure(Call<RegiserResponse> call, Throwable t) {
                String message = t.getMessage();
                progressDialog.dismiss();
                Log.d("failure", message);
                // System.out.println("===========fail========" + message);
                call.cancel();
                //mLoding_Spinner.setVisibility(View.GONE);
                Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

}
