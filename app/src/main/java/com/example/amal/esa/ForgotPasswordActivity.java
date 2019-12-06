package com.example.amal.esa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ForgotPasswordActivity extends AppCompatActivity {
    WebView moWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        String lsUrl = "https://es-embassy.herokuapp.com/accounts/password_reset/";
        moWebView = (WebView) findViewById(R.id.webview);
        moWebView.getSettings().setJavaScriptEnabled(true);
        moWebView.getSettings().setGeolocationEnabled(true);
        moWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //moWebView.getSettings().setBuiltInZoomControls(true);
        moWebView.getSettings().setDatabaseEnabled(true);
        moWebView.getSettings().setDomStorageEnabled(true);
        moWebView.getSettings().setAppCacheEnabled(true);
        moWebView.setWebViewClient(new CustomWebViewClient());
       // moWebView.setWebChromeClient(new CustomWebChromeClient());
        moWebView.getSettings().setGeolocationDatabasePath(ForgotPasswordActivity.this.getFilesDir().getPath());
        moWebView.getSettings().setUserAgentString(moWebView.getSettings().getUserAgentString().replace("; wv", ""));
        moWebView.loadUrl(lsUrl);
    }

    public class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            System.out.println("=====Url=="+url);

            return super.shouldOverrideUrlLoading(view, url);
        }
    }



}
