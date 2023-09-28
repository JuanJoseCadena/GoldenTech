package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjca.goldentech.db.DBHelper;

public class Actividades_ludicas extends AppCompatActivity {

    CardView cardView;
    LinearLayout activityLayout;
    Spinner activityType;
    TextView solved;
    int solved_activities = 0;
    String status = "run";
    String tipo_actividad = "Todas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades_ludicas);

        status = "run";

        tipo_actividad = "Todas";

        solved = (TextView) findViewById(R.id.solved_activities);

        activityLayout = (LinearLayout) findViewById(R.id.ludica_layout);

        DBHelper dbHelper = new DBHelper(Actividades_ludicas.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM play_activities WHERE id >= ?", new String[] {"1"});

        activityType = (Spinner) findViewById(R.id.ludica_spinner);

        String[] actividades = new String[]{"Todas","Sopa De Letras","Crucigrama","Memoria","Asociación O Relación","Destreza","Destreza Y Conocimiento","Adivinanza"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Actividades_ludicas.this,android.R.layout.simple_spinner_dropdown_item, actividades);
        activityType.setAdapter(adapter);

        activityType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_type = parent.getItemAtPosition(position).toString();
                Cursor dataFilter;

                tipo_actividad = selected_type;

                if(selected_type.equals("Todas")) {
                    dataFilter = db.rawQuery("SELECT * FROM play_activities WHERE id >= ?", new String[] {"1"});
                }
                else{
                    dataFilter = db.rawQuery("SELECT * FROM play_activities WHERE activity_type = ?", new String[]{selected_type});
                }

                getActividades(dataFilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        getActividades(data);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        status = "pause";
    }

    @Override
    public void onResume(){
        super.onResume();

        if(status.equals("pause"))
        {
            status = "run";
            int solucionadas = 0;

            DBHelper dbHelper = new DBHelper(Actividades_ludicas.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor data;
            if(tipo_actividad.equals("Todas")) {
                data = db.rawQuery("SELECT * FROM play_activities WHERE id >= ?", new String[] {"1"});
            }
            else{
                data = db.rawQuery("SELECT * FROM play_activities WHERE activity_type = ?", new String[]{tipo_actividad});
            }

            data.moveToFirst();

            for(int i = 0; i < data.getCount(); i++)
            {
                String times_solved = data.getString(data.getColumnIndex("times_solved"));
                int solution = Integer.parseInt(times_solved);

                if(solution >= 1)
                {
                    solucionadas++;
                }

                data.moveToNext();
            }

            solved.setText("Actividades Realizadas: " + solucionadas);
        }
    }

    public void startMainActivity(View view)
    {
        Intent intent  = new Intent(Actividades_ludicas.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void startNewsActivity(View view)
    {
        Intent intent  = new Intent(Actividades_ludicas.this, Noticias.class);
        startActivity(intent);
        finish();
    }

    public void startContactActivity(View view)
    {
        Intent intent  = new Intent(Actividades_ludicas.this, Contactos.class);
        startActivity(intent);
        finish();
    }

    public void startMedicosActivity(View view)
    {
        Intent intent  = new Intent(Actividades_ludicas.this, Especialistas_medicos.class);
        startActivity(intent);
        finish();
    }

    public void startLudicasActivity(View view)
    {
        //Intent intent  = new Intent(Actividades_ludicas.this, Actividades_ludicas.class);
        //startActivity(intent);
        //finish();
    }

    public void startDiagnosticoActivity(View view)
    {
        Intent intent  = new Intent(Actividades_ludicas.this, Diagnostico_medico.class);
        startActivity(intent);
        finish();
    }

    public void getActividades(Cursor data){
        activityLayout.removeAllViews();

        solved_activities = 0;

        data.moveToFirst();

        for(int i = 0; i < data.getCount(); i++)
        {
            cardView = new CardView(Actividades_ludicas.this);
            LinearLayout.LayoutParams card_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            card_params.setMargins(55,30,83,15);
            cardView.setLayoutParams(card_params);
            cardView.setCardBackgroundColor(Color.parseColor("#F5B2FF"));
            cardView.setRadius(20);
            cardView.setCardElevation(10);


            LinearLayout layout = new LinearLayout(Actividades_ludicas.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setLayoutParams(lay_params);

            ImageView image = new ImageView(Actividades_ludicas.this);
            LinearLayout.LayoutParams image_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            image_params.setMargins(0,28,0,28);
            image_params.gravity = Gravity.CENTER_HORIZONTAL;
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setAdjustViewBounds(true);
            image.setLayoutParams(image_params);

            String actype = data.getString(data.getColumnIndex("activity_type"));

            switch(actype)
            {
                case "Sopa De Letras":
                    image.setImageResource(R.mipmap.word);
                    break;
                case "Crucigrama":
                    image.setImageResource(R.mipmap.crossword);
                    break;
                case "Memoria":
                    image.setImageResource(R.mipmap.memory);
                    break;
                case "Asociación O Relación":
                    image.setImageResource(R.mipmap.matching);
                    break;
                case "Destreza":
                    image.setImageResource(R.mipmap.puzzle);
                    break;
                case "Destreza Y Conocimiento":
                    image.setImageResource(R.mipmap.competence);
                    break;
                case "Adivinanza":
                    image.setImageResource(R.mipmap.logical_thinking);
                    break;
            }

            TextView titulo = new TextView(Actividades_ludicas.this);
            LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            text_params.setMargins(14,0,14,0);
            titulo.setLayoutParams(text_params);
            Typeface typeface = ResourcesCompat.getFont(Actividades_ludicas.this,R.font.open_sans_semi_bold);
            titulo.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            titulo.setText(data.getString(data.getColumnIndex("activity_name")));
            titulo.setTextColor(Color.parseColor("#000000"));
            titulo.setTextSize(25);
            titulo.setTypeface(typeface);

            String desc = data.getString(data.getColumnIndex("activity_type")) + "\nTema: " +data.getString(data.getColumnIndex("category"));

            TextView descripcion = new TextView(Actividades_ludicas.this);
            text_params.setMargins(14,14,14,21);
            descripcion.setLayoutParams(text_params);
            Typeface typeface_desc = ResourcesCompat.getFont(Actividades_ludicas.this,R.font.open_sans_light);
            //descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            descripcion.setText(desc);
            descripcion.setTextColor(Color.parseColor("#000000"));
            descripcion.setTextSize(18);
            descripcion.setTypeface(typeface_desc);

            layout.addView(image);
            layout.addView(titulo);
            layout.addView(descripcion);
            cardView.addView(layout);

            String activity_url = data.getString(data.getColumnIndex("connection_point"));
            String times_solved = data.getString(data.getColumnIndex("times_solved"));

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(Actividades_ludicas.this, Ludica_WebView.class);
                    intent.putExtra("activity_url", activity_url);
                    intent.putExtra("times_solved", times_solved);
                    startActivity(intent);
                }
            });

            activityLayout.addView(cardView);

            int solution = Integer.parseInt(times_solved);

            if(solution >= 1)
            {
                solved_activities++;
            }

            data.moveToNext();
        }

        solved.setText("Actividades Realizadas: " + solved_activities);
    }

}