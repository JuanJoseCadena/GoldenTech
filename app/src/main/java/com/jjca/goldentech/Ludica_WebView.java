package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjca.goldentech.db.DBHelper;


public class Ludica_WebView extends AppCompatActivity {

    WebView actividad;
    int count_aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ludica_web_view);

        DBHelper dbHelper = new DBHelper(Ludica_WebView.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Bundle bundle = getIntent().getExtras();
        String base_url = bundle.getString("activity_url");
        String times_solved = bundle.getString("times_solved");

        count_aux = 0;

        String url = base_url.replace("recursos-educativos","juego");

        actividad = (WebView) findViewById(R.id.ludica_webview);
        actividad.setWebViewClient(new WebViewClient());
        actividad.setWebChromeClient(new WebChromeClient());
        actividad.getSettings().setLoadWithOverviewMode(true);
        actividad.getSettings().setUseWideViewPort(true);
        actividad.getSettings().setJavaScriptEnabled(true);
        actividad.getSettings().setPluginState(WebSettings.PluginState.ON);
        actividad.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        actividad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    count_aux = count_aux + 1;

                    if(count_aux == 5)
                    {
                        int solution = Integer.parseInt(times_solved);

                        ContentValues values = new ContentValues();
                        values.put("times_solved",solution+1);

                        db.update("play_activities",values,"connection_point = ?", new String[] {base_url});


                    }
                }

                return false;
            }
        });

        actividad.loadUrl(url);
    }

}