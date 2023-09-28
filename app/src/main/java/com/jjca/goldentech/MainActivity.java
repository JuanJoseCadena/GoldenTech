package com.jjca.goldentech;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import com.jjca.goldentech.db.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar;
    String status = "init";
    int starRating;
    int starAux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = "init";
        starAux = 0;

        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    @Override
    public void onPause()
    {
        super.onPause();
        status = "pause";
        starAux++;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(status.equals("pause"))
        {
            status = "run";

            if(starAux == 5) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Dános tu Opinión");
                builder.setMessage("Dinos en una escala del 1 al 5 que tan buena te ha parecido la aplicación");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        try {
                            DBHelper dbHelper = new DBHelper(MainActivity.this);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();

                            Cursor existingData = db.rawQuery("SELECT * FROM app_ratings WHERE id >= ?", new String[]{"1"});

                            int id = 1;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            String fecha = dateFormat.format(date);


                            if (existingData.getCount() >= 1) {
                                existingData.moveToLast();
                                id = Integer.parseInt(existingData.getString(existingData.getColumnIndex("id")));
                                id = id + 1;
                            }

                            ContentValues values = new ContentValues();
                            values.put("id", id);
                            values.put("rate", starRating);
                            values.put("date", fecha);
                            values.put("user", 1);

                            db.insert("app_ratings", null, values);

                            Toast.makeText(MainActivity.this, "Muchas gracias, se ha registrado su opinión", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Ha ocurrido un error al procesar su solicitud", Toast.LENGTH_LONG).show();

                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layout.setLayoutParams(lay_params);

                ratingBar = new RatingBar(MainActivity.this);
                LinearLayout.LayoutParams star_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                star_params.setMargins(0,50,0,0);
                star_params.gravity = Gravity.CENTER_HORIZONTAL;
                ratingBar.setLayoutParams(star_params);
                ratingBar.setNumStars(5);
                ratingBar.setStepSize(1);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        starRating = (int) rating;
                        setCurrentRating(rating);
                    }
                });

                layout.addView(ratingBar);

                starAux = 0;

                builder.setView(layout);
                AlertDialog starPop = builder.create();
                starPop.show();
            }
        }
    }

    public void startMainActivity(View view)
    {
        //Intent intent  = new Intent(MainActivity.this, MainActivity.class);
        //startActivity(intent);
    }

    public void startNewsActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, Noticias.class);
        startActivity(intent);
    }

    public void startContactActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, Contactos.class);
        startActivity(intent);
    }

    public void startMedicosActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, Especialistas_medicos.class);
        startActivity(intent);
    }

    public void startLudicasActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, Actividades_ludicas.class);
        startActivity(intent);
    }

    public void startDiagnosticoActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, Diagnostico_medico.class);
        startActivity(intent);
    }

    public void startReferencesActivity(View view)
    {
        Intent intent  = new Intent(MainActivity.this, References.class);
        startActivity(intent);
    }

    public void setCurrentRating(float rating)
    {
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();

        if(MainActivity.this != null)
        {

            setRatingStarColor(stars.getDrawable(2), Color.parseColor("#D4AF37"));
            setRatingStarColor(stars.getDrawable(1), Color.parseColor("#00000000"));

        }

    }

    private void setRatingStarColor(Drawable drawable, @ColorInt int color)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            DrawableCompat.setTint(drawable, color);
        }
        else{
            drawable.setColorFilter(color,PorterDuff.Mode.SRC_IN);
        }
    }
}