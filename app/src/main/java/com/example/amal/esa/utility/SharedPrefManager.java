package com.example.amal.esa.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.amal.esa.model.LoginResponse;
import com.google.gson.Gson;

public class SharedPrefManager {

    public static final String PREFS_NAME = "nu_pref";
    public static final String LOGIN_ = "login_";
    public static final String ACTIVE_FLAG = "active_flag";
    public static final String SEARCH_PROPERTY_RESPONSE = "search_property";
    public static final String LOAN_NUMBER = "loan_number";
    public static final String CUSTOMER_NAME = "customer_name";
    public static final String BANK_NAME = "bank_name";
    public static final String ACCOUNT_NUMBER = "account_number";

    public SharedPrefManager() {
        super();
    }


    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }


    public void logOut(Context context) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.apply();
    }


  /*  public void setMerchantName(Context context, String merchantName) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(LOGIN_, merchantName);
        editor.commit();
    }*/


    public void saveUserDeatail(Context context, LoginResponse user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(LOGIN_, json);
        editor.commit();
    }

    public LoginResponse getUserDetail(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(LOGIN_, "");
        LoginResponse obj = gson.fromJson(json, LoginResponse.class);
        return obj;
    }


   /* public void activeFlag(Context context, ActiveFlag user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(ACTIVE_FLAG, json);
        editor.commit();
    }

    public ActiveFlag getActiveFlag(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(ACTIVE_FLAG, "");
        ActiveFlag obj = gson.fromJson(json, ActiveFlag.class);
        return obj;
    }


  *//*  public void saveMerchantDeatail(Context context, LoginResponse user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("merchant_profile", json);
        editor.commit();
    }

    public LoginResponse getMerchantDetail(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("merchant_profile", "");
        LoginResponse obj = gson.fromJson(json, LoginResponse.class);
        return obj;
    }*//*

    public void saveReg(Context context, String user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("Reg", user);
        editor.commit();
    }


    public String getRegData(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPref.getString("Reg_detail", "");
        return json;
    }



    public void saveReg_detail(Context context, String user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("Reg_detail", user);
        editor.commit();
    }


    public String getRegData_detail(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPref.getString("Reg_detail", "");
        return json;
    }


    public void saveCount(Context context, String count) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString("count", count);
        editor.commit();
    }


    public String getCount(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPref.getString("count", null);
        return json;
    }


    public void saveSearchProperty(Context context, SearchPropertyResponse user) {
        SharedPreferences sharedPref;
        Editor editor;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(SEARCH_PROPERTY_RESPONSE, json);
        editor.commit();
    }

    public SearchPropertyResponse getSearchPropertyResponse(Context context) {
        SharedPreferences sharedPref;
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString(SEARCH_PROPERTY_RESPONSE, "");
        SearchPropertyResponse obj = gson.fromJson(json, SearchPropertyResponse.class);
        return obj;
    }*/
}