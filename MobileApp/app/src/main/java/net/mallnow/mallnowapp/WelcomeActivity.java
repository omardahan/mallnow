package net.mallnow.mallnowapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WelcomeActivity extends AppCompatActivity {

    private WebView mallNowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mallNowView = (WebView)findViewById(R.id.mallNowView);
        WebSettings webSettings = mallNowView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mallNowView.loadUrl("https://mallnow.mshamia.com/home/mobileview");
        mallNowView.setWebViewClient(new WebViewClient());

    }
}
