package com.studentinfo.admin.mattress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.List;

/**
 * Created by santo on 24-04-2016.
 */
public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    TextView Device_id;
    String android_id;
    static String ipaddress;
    Button Submit;
    private ProgressDialog pd;
    EditText Ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this, "SAUlnW7x11OaF0QvLYeupepjyRVRNm3t8FAl8yp9", "MfvemfzV0e7GIA0aKi81z8jYlRgxjK8TWVmCu4Q4");
            ParseInstallation.getCurrentInstallation().saveInBackground();

        } catch (IllegalStateException io) {
            io.printStackTrace();
        }

        android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Device_id = (TextView) findViewById(R.id.Device_id);
        Ip = (EditText) findViewById(R.id.ip);
        Submit=(Button)findViewById(R.id.Submit);


        Device_id.setText("Your Device id Is: " + android_id);

//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("USerdetails");
//        query.whereEqualTo("Device_ID", android_id);
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, com.parse.ParseException e) {
//
//
//                if (e == null) {
//
//
//                    if(objects.size()> 0) {
//
//
//                        pd = new ProgressDialog(SplashScreen.this);
//                        pd.setMessage("You are already registered with us..");
//                        pd.show();
//
//                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
//                        startActivity(i);
//                        Log.d("score", "Retrieved " + objects.size() + " scores");
//                    }
//                } else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomain();
            }
        });
    }
    public void gotomain(){

        ipaddress=Ip.getText().toString().trim();

        if (isNetworkStatusAvialable(getApplicationContext())) {


            if (!TextUtils.isEmpty(Ip.getText().toString().trim())) {
                ParseObject gameScore = new ParseObject("USerdetails");
                gameScore.put("Device_ID", android_id);
                gameScore.put("IP_Address",ipaddress);
                gameScore.saveInBackground();

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Please enter website Ip Address", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection", Toast.LENGTH_SHORT).show();

            finish();
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
}
