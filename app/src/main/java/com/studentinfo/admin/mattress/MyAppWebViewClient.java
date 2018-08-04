package com.studentinfo.admin.mattress;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by santo on 23-04-2016.
 */
public class MyAppWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return super.shouldOverrideUrlLoading(view, url);
        if(Uri.parse(url).getHost().endsWith(SplashScreen.ipaddress));
        return false;

    }
//    Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
//    view.getContext().startActivity(intent);
//    return true;

}
