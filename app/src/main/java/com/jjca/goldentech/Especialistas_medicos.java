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
import android.widget.Toast;

import com.jjca.goldentech.db.DBHelper;

public class Especialistas_medicos extends AppCompatActivity {

    CardView cardView;
    LinearLayout medicalLayout;
    Spinner medicalDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialistas_medicos);

        medicalLayout = (LinearLayout) findViewById(R.id.medical_layout);

        DBHelper dbHelper = new DBHelper(Especialistas_medicos.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM medical_contact WHERE id >= ?", new String[] {"1"});

        medicalDegree = (Spinner) findViewById(R.id.medical_spinner);

        String[] especialidades = new String[]{"Todas","Médico familiar","Geriatra","Cardióloga","Internista","Nefrólogo","Neumólogo","Neurólogo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Especialistas_medicos.this,android.R.layout.simple_spinner_dropdown_item, especialidades);
        medicalDegree.setAdapter(adapter);

        medicalDegree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_degree = parent.getItemAtPosition(position).toString();
                Cursor dataFilter;

                if(selected_degree.equals("Todas")) {
                    dataFilter = db.rawQuery("SELECT * FROM medical_contact WHERE id >= ?", new String[] {"1"});
                }
                else{
                    dataFilter = db.rawQuery("SELECT * FROM medical_contact WHERE medical_degree = ?", new String[]{selected_degree});
                }

                getMedicos(dataFilter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        getMedicos(data);

    }

    public void startMainActivity(View view)
    {
        Intent intent  = new Intent(Especialistas_medicos.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void startNewsActivity(View view)
    {
        Intent intent  = new Intent(Especialistas_medicos.this, Noticias.class);
        startActivity(intent);
        finish();
    }

    public void startContactActivity(View view)
    {
        Intent intent  = new Intent(Especialistas_medicos.this, Contactos.class);
        startActivity(intent);
        finish();
    }

    public void startMedicosActivity(View view)
    {
        //Intent intent  = new Intent(Especialistas_medicos.this, Especialistas_medicos.class);
        //startActivity(intent);
        //finish();
    }

    public void startLudicasActivity(View view)
    {
        Intent intent  = new Intent(Especialistas_medicos.this, Actividades_ludicas.class);
        startActivity(intent);
        finish();
    }

    public void startDiagnosticoActivity(View view)
    {
        Intent intent  = new Intent(Especialistas_medicos.this, Diagnostico_medico.class);
        startActivity(intent);
        finish();
    }

    public void getMedicos(Cursor data)
    {
        medicalLayout.removeAllViews();

        data.moveToFirst();

        for(int i = 0; i < data.getCount(); i++)
        {
            cardView = new CardView(Especialistas_medicos.this);
            LinearLayout.LayoutParams card_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            card_params.setMargins(55,30,83,15);
            cardView.setLayoutParams(card_params);
            cardView.setCardBackgroundColor(Color.parseColor("#BBFCDF"));
            cardView.setRadius(20);
            cardView.setCardElevation(10);


            LinearLayout layout = new LinearLayout(Especialistas_medicos.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setLayoutParams(lay_params);

            ImageView image = new ImageView(Especialistas_medicos.this);
            LinearLayout.LayoutParams image_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 240);
            image_params.setMargins(0,28,0,28);
            image_params.gravity = Gravity.CENTER_HORIZONTAL;
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setAdjustViewBounds(true);
            image.setLayoutParams(image_params);

            if(data.getString(data.getColumnIndex("gender")).equals("Masculino")) {
                image.setImageResource(R.mipmap.doctor_male);
            }
            else{
                image.setImageResource(R.mipmap.doctor);
            }

            TextView titulo = new TextView(Especialistas_medicos.this);
            LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            text_params.setMargins(14,0,14,0);
            titulo.setLayoutParams(text_params);
            Typeface typeface = ResourcesCompat.getFont(Especialistas_medicos.this,R.font.open_sans_semi_bold);
            titulo.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            titulo.setText(data.getString(data.getColumnIndex("name")));
            titulo.setTextColor(Color.parseColor("#000000"));
            titulo.setTextSize(25);
            titulo.setTypeface(typeface);

            String desc = data.getString(data.getColumnIndex("medical_degree")) + "\nTeléfono: " +data.getString(data.getColumnIndex("phone"));

            TextView descripcion = new TextView(Especialistas_medicos.this);
            text_params.setMargins(14,14,14,21);
            descripcion.setLayoutParams(text_params);
            Typeface typeface_desc = ResourcesCompat.getFont(Especialistas_medicos.this,R.font.open_sans_light);
            //descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            descripcion.setText(desc);
            descripcion.setTextColor(Color.parseColor("#000000"));
            descripcion.setTextSize(18);
            descripcion.setTypeface(typeface_desc);

            ImageView call  = new ImageView(Especialistas_medicos.this);
            LinearLayout.LayoutParams call_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 120);
            call_params.setMargins(0,30,0,28);
            call_params.gravity = Gravity.CENTER_HORIZONTAL;
            call.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            call.setAdjustViewBounds(true);
            call.setLayoutParams(call_params);
            call.setImageResource(R.mipmap.phone_call);

            String phone = data.getString(data.getColumnIndex("phone"));

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + phone));
                        startActivity(callIntent);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(Especialistas_medicos.this,"Ha ocurrido un error al procesar la solicitud",Toast.LENGTH_LONG).show();
                    }
                }
            });

            layout.addView(image);
            layout.addView(titulo);
            layout.addView(descripcion);
            layout.addView(call);
            cardView.addView(layout);
            medicalLayout.addView(cardView);

            data.moveToNext();
        }
    }
}