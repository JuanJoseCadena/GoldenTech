package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Noticia_WebView extends AppCompatActivity {

    WebView news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia_web_view);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("news_url");

        news = (WebView) findViewById(R.id.news_webview);
        news.setWebViewClient(new WebViewClient());
        news.setWebChromeClient(new WebChromeClient());
        news.getSettings().setLoadWithOverviewMode(true);
        news.getSettings().setUseWideViewPort(true);
        news.getSettings().setJavaScriptEnabled(true);
        news.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        news.loadUrl(url);

    }
}