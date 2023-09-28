package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Noticias extends AppCompatActivity {

    private static final String API_KEY = "b4edacc1aed282acb8e4a45f84094545";

    private static String url_request = "http://api.mediastack.com/v1/news?access_key="+API_KEY+"&languages=es&limit=15&countries=co";

    LinearLayout cardLayout;
    CardView cardView;
    Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        cardLayout = (LinearLayout) findViewById(R.id.cardLayout);

        category = (Spinner) findViewById(R.id.category_spinner);

        String[] categorias = new String[]{"Todas","General","Negocios","Entretenimiento","Salud","Ciencia","Deportes","Tecnología"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Noticias.this,android.R.layout.simple_spinner_dropdown_item, categorias);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_category = parent.getItemAtPosition(position).toString();

                String url_category = "";

                if(selected_category.equals("Todas"))
                {
                    url_request = "http://api.mediastack.com/v1/news?access_key="+API_KEY+"&languages=es&limit=15&countries=co";
                }
                else {
                    switch (selected_category) {
                        case "General":
                            url_category = "&categories=general";
                            break;
                        case "Negocios":
                            url_category = "&categories=business";
                            break;
                        case "Entretenimiento":
                            url_category = "&keywords=entretenimiento";
                            break;
                        case "Salud":
                            url_category = "&keywords=salud";
                            break;
                        case "Ciencia":
                            url_category = "&categories=science";
                            break;
                        case "Deportes":
                            url_category = "&categories=sports";
                            break;
                        case "Tecnología":
                            url_category = "&categories=technology";
                            break;
                        default:
                            break;
                    }

                    url_request = "http://api.mediastack.com/v1/news?access_key="+API_KEY+"&languages=es&limit=15" + url_category;
                }

                getNoticias();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        //getNoticias();
    }

    public void startMainActivity(View view)
    {
        Intent intent  = new Intent(Noticias.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void startNewsActivity(View view)
    {
        //Intent intent  = new Intent(Noticias.this, Noticias.class);
        //startActivity(intent);
        //finish();
    }

    public void startContactActivity(View view)
    {
        Intent intent  = new Intent(Noticias.this, Contactos.class);
        startActivity(intent);
        finish();
    }

    public void startMedicosActivity(View view)
    {
        Intent intent  = new Intent(Noticias.this, Especialistas_medicos.class);
        startActivity(intent);
        finish();
    }

    public void startLudicasActivity(View view)
    {
        Intent intent  = new Intent(Noticias.this, Actividades_ludicas.class);
        startActivity(intent);
        finish();
    }

    public void startDiagnosticoActivity(View view)
    {
        Intent intent  = new Intent(Noticias.this, Diagnostico_medico.class);
        startActivity(intent);
        finish();
    }

    public void getNoticias()
    {
        cardLayout.removeAllViews();

        RequestQueue queue = Volley.newRequestQueue(Noticias.this);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,url_request,null,(Response.Listener<JSONObject>) response -> {
            try {
                String respuesta = response.getString("data");

                ArrayList<String> newsArray = new ArrayList<String>();

                JSONArray jsonArray = new JSONArray(respuesta);

                for(int i = 0; i < jsonArray.length(); i++) {
                    newsArray.add(jsonArray.getString(i));
                }

                for(int x = 0; x < newsArray.toArray().length; x++)
                {
                    JSONObject jsonNews = new JSONObject(newsArray.get(x));

                    cardView = new CardView(Noticias.this);
                    LinearLayout.LayoutParams card_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    card_params.setMargins(55,30,83,15);
                    cardView.setLayoutParams(card_params);
                    cardView.setCardBackgroundColor(Color.parseColor("#F5DABA"));
                    cardView.setRadius(20);
                    cardView.setCardElevation(10);


                    LinearLayout layout = new LinearLayout(Noticias.this);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    layout.setLayoutParams(lay_params);

                    ImageView image = new ImageView(Noticias.this);
                    LinearLayout.LayoutParams image_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    image_params.setMargins(0,0,0,28);
                    image_params.gravity = Gravity.CENTER_HORIZONTAL;
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setAdjustViewBounds(true);
                    image.setLayoutParams(image_params);

                    String news_img = jsonNews.getString("image").replace("\\","");
                    if(news_img.contains("?")) {
                        news_img = news_img.substring(0, news_img.indexOf("?"));
                    }

                    TextView titulo = new TextView(Noticias.this);
                    LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    text_params.setMargins(14,0,14,0);
                    titulo.setLayoutParams(text_params);
                    Typeface typeface = ResourcesCompat.getFont(Noticias.this,R.font.open_sans_semi_bold);
                    titulo.setGravity(Gravity.CENTER_HORIZONTAL);
                    //titulo.setLineHeight(32);
                    titulo.setText(jsonNews.getString("title"));
                    titulo.setTextColor(Color.parseColor("#000000"));
                    titulo.setTextSize(25);
                    titulo.setTypeface(typeface);

                    String fecha = jsonNews.getString("published_at").substring(0,10);
                    String news_url = jsonNews.getString("url").replace("\\","");

                    String categoria = "";

                    switch(jsonNews.getString("category"))
                    {
                        case "general":
                            categoria = "General";
                            break;
                        case "business":
                            categoria = "Negocios";
                            break;
                        case "entertainment":
                            categoria = "Entretenimiento";
                            break;
                        case "health":
                            categoria = "Salud";
                            break;
                        case "science":
                            categoria = "Ciencia";
                            break;
                        case "sports":
                            categoria = "Deportes";
                            break;
                        case "technology":
                            categoria = "Tecnología";
                            break;

                    }

                    if(category.getSelectedItem().toString() == "Salud")
                    {
                        categoria = "Salud";
                    }
                    else if(category.getSelectedItem().toString() == "Entretenimiento")
                    {
                        categoria = "Entretenimiento";
                    }

                    String desc = "Fuente: " + jsonNews.getString("source") + "\nFecha: " + fecha + "\nCategoría: " + categoria;

                    TextView descripcion = new TextView(Noticias.this);
                    text_params.setMargins(14,14,14,21);
                    descripcion.setLayoutParams(text_params);
                    Typeface typeface_desc = ResourcesCompat.getFont(Noticias.this,R.font.open_sans_light);
                    //descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
                    //titulo.setLineHeight(32);
                    descripcion.setText(desc);
                    descripcion.setTextColor(Color.parseColor("#000000"));
                    descripcion.setTextSize(18);
                    descripcion.setTypeface(typeface_desc);

                    Picasso.get().load(news_img).placeholder(R.mipmap.photo).error(R.mipmap.delete).resize(cardView.getWidth(),1000).centerInside().into(image);

                    layout.addView(image);
                    layout.addView(titulo);
                    layout.addView(descripcion);
                    cardView.addView(layout);

                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent  = new Intent(Noticias.this, Noticia_WebView.class);
                            intent.putExtra("news_url", news_url);
                            startActivity(intent);

                        }
                    });

                    cardLayout.addView(cardView);

                }

            }
            catch(JSONException e)
            {
                Toast.makeText(Noticias.this, "Ha ocurrido un error\n"+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }, (Response.ErrorListener) error -> {
            Toast.makeText(Noticias.this, error.getMessage(), Toast.LENGTH_LONG).show();
        });

        queue.add(jsonRequest);
    }

}