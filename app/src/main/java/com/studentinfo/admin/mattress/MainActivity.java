package com.studentinfo.admin.mattress;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //detect internet and show the data
        if(isNetworkStatusAvialable (getApplicationContext())) {

            mwebView=(WebView)findViewById(R.id.opensite);
            WebSettings webSettings= mwebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            mwebView.loadUrl("http://www.google.com");
//        mwebView.setWebViewClient(new WebViewClient());
            mwebView.setWebViewClient(new MyAppWebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    findViewById(R.id.splashloding).setVisibility(View.GONE);

                    findViewById(R.id.opensite).setVisibility(View.VISIBLE);
                    super.onPageFinished(view, url);
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }


    //check internet connection
    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }

    private class MyAppWebViewClient extends WebViewClient{

    }

    @Override
    public void onBackPressed() {
        if(mwebView.canGoBack()){
            mwebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
