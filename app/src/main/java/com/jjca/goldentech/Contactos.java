package com.jjca.goldentech;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjca.goldentech.db.DBHelper;

public class Contactos extends AppCompatActivity {

    LinearLayout contact_layout;
    Button contactbtn;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        contactbtn = (Button) findViewById(R.id.contactbtn);

        contact_layout = (LinearLayout) findViewById(R.id.contact_layout);

        ActivityResultLauncher<Intent> contactLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK){

                    try {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                        cursor.moveToFirst();
                        int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        String phone = cursor.getString(phoneIndex).replace("+57", "").replace("-", "").replace(" ", "");
                        int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                        String name = cursor.getString(nameIndex);

                        DBHelper dbHelper = new DBHelper(Contactos.this);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        Cursor existingData = db.rawQuery("SELECT * FROM contact_info WHERE id >= ?", new String[] {"1"});

                        int id = 1;
                        boolean exists = false;

                        if(existingData.getCount() >= 1)
                        {
                            existingData.moveToLast();
                            id = Integer.parseInt(existingData.getString(existingData.getColumnIndex("id")));
                            id = id + 1;

                            existingData.moveToFirst();

                            for(int i = 0;i < existingData.getCount();i++){
                                if(existingData.getString(existingData.getColumnIndex("phone")).equals(phone))
                                {
                                    exists = true;
                                }

                                existingData.moveToNext();
                            }
                        }

                        if(exists){

                            Toast.makeText(Contactos.this, "El contacto seleccionado ya se encuentra en la aplicación", Toast.LENGTH_LONG).show();
                        }
                        else {
                            ContentValues values = new ContentValues();
                            values.put("id", id);
                            values.put("name", name);
                            values.put("phone", phone);
                            values.put("gender", "");
                            values.put("user_contact", 1);

                            db.insert("contact_info", null, values);

                            Toast.makeText(Contactos.this, "Se ha importado el contacto correctamente", Toast.LENGTH_LONG).show();

                            Cursor new_data = db.rawQuery("SELECT * FROM contact_info WHERE id >= ?", new String[]{"1"});

                            getContactos(new_data);
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(Contactos.this,"Ha ocurrido un error al cargar el contacto",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                contactLauncher.launch(contactIntent);
            }
        });

        DBHelper dbHelper = new DBHelper(Contactos.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor contact_data = db.rawQuery("SELECT * FROM contact_info WHERE id >= ?", new String[] {"1"});
        getContactos(contact_data);
    }

    public void startMainActivity(View view)
    {
        Intent intent  = new Intent(Contactos.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void startNewsActivity(View view)
    {
        Intent intent  = new Intent(Contactos.this, Noticias.class);
        startActivity(intent);
        finish();
    }

    public void startContactActivity(View view)
    {
        //Intent intent  = new Intent(Contactos.this, Contactos.class);
        //startActivity(intent);
        //finish();
    }

    public void startMedicosActivity(View view)
    {
        Intent intent  = new Intent(Contactos.this, Especialistas_medicos.class);
        startActivity(intent);
        finish();
    }

    public void startLudicasActivity(View view)
    {
        Intent intent  = new Intent(Contactos.this, Actividades_ludicas.class);
        startActivity(intent);
        finish();
    }

    public void startDiagnosticoActivity(View view)
    {
        Intent intent  = new Intent(Contactos.this, Diagnostico_medico.class);
        startActivity(intent);
        finish();
    }

    public void getContactos(Cursor data)
    {
        contact_layout.removeAllViews();

        data.moveToFirst();

        for(int i = 0; i < data.getCount(); i++)
        {
            cardView = new CardView(Contactos.this);
            LinearLayout.LayoutParams card_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            card_params.setMargins(55,30,83,15);
            cardView.setLayoutParams(card_params);
            cardView.setCardBackgroundColor(Color.parseColor("#B3D0FF"));
            cardView.setRadius(20);
            cardView.setCardElevation(10);


            LinearLayout layout = new LinearLayout(Contactos.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layout.setLayoutParams(lay_params);

            ImageView image = new ImageView(Contactos.this);
            LinearLayout.LayoutParams image_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 240);
            image_params.setMargins(0,28,0,28);
            image_params.gravity = Gravity.CENTER_HORIZONTAL;
            image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            image.setAdjustViewBounds(true);
            image.setLayoutParams(image_params);
            image.setImageResource(R.mipmap.user_new);


            TextView titulo = new TextView(Contactos.this);
            LinearLayout.LayoutParams text_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            text_params.setMargins(14,0,14,0);
            titulo.setLayoutParams(text_params);
            Typeface typeface = ResourcesCompat.getFont(Contactos.this,R.font.open_sans_semi_bold);
            titulo.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            titulo.setText(data.getString(data.getColumnIndex("name")));
            titulo.setTextColor(Color.parseColor("#000000"));
            titulo.setTextSize(25);
            titulo.setTypeface(typeface);

            String desc = "Teléfono: " +data.getString(data.getColumnIndex("phone"));

            TextView descripcion = new TextView(Contactos.this);
            text_params.setMargins(14,14,14,51);
            descripcion.setLayoutParams(text_params);
            Typeface typeface_desc = ResourcesCompat.getFont(Contactos.this,R.font.open_sans_light);
            //descripcion.setGravity(Gravity.CENTER_HORIZONTAL);
            //titulo.setLineHeight(32);
            descripcion.setText(desc);
            descripcion.setTextColor(Color.parseColor("#000000"));
            descripcion.setTextSize(18);
            descripcion.setTypeface(typeface_desc);

            ImageView call  = new ImageView(Contactos.this);
            LinearLayout.LayoutParams call_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 120);
            call_params.setMargins(60,30,0,28);
            call_params.gravity = Gravity.CENTER_HORIZONTAL;
            call.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            call.setAdjustViewBounds(true);
            call.setLayoutParams(call_params);
            call.setImageResource(R.mipmap.phone_call);

            ImageView delete = new ImageView(Contactos.this);
            LinearLayout.LayoutParams delete_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 120);
            delete_params.setMargins(60,30,0,28);
            delete_params.gravity = Gravity.CENTER_HORIZONTAL;
            delete.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            delete.setAdjustViewBounds(true);
            delete.setLayoutParams(delete_params);
            delete.setImageResource(R.mipmap.trash_bin);

            LinearLayout btn_layout = new LinearLayout(Contactos.this);
            btn_layout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams btnlay_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            btn_layout.setLayoutParams(btnlay_params);

            String phone = data.getString(data.getColumnIndex("phone"));
            String id = data.getString(data.getColumnIndex("id"));

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
                        Toast.makeText(Contactos.this,"Ha ocurrido un error al procesar la solicitud",Toast.LENGTH_LONG).show();
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPopUp(id);
                }
            });

            btn_layout.addView(call);
            btn_layout.addView(delete);
            layout.addView(image);
            layout.addView(titulo);
            layout.addView(descripcion);
            layout.addView(btn_layout);
            cardView.addView(layout);
            contact_layout.addView(cardView);

            data.moveToNext();
        }
    }

    public void getPopUp(String id)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Contactos.this);
        builder.setCancelable(true);
        builder.setTitle("¿Estas Seguro?");
        builder.setMessage("Al aceptar, eliminarás de la aplicación el contacto seleccionado");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper dbHelper = new DBHelper(Contactos.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("contact_info","id = ?",new String[]{id});

                Toast.makeText(Contactos.this, "Se ha eliminado el contacto correctamente", Toast.LENGTH_LONG).show();

                Cursor new_data = db.rawQuery("SELECT * FROM contact_info WHERE id >= ?", new String[]{"1"});
                getContactos(new_data);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog delete_popup = builder.create();
        delete_popup.show();
    }
}