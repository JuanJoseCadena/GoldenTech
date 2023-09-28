package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Diagnostico_medico extends AppCompatActivity {

    EditText sistolica;
    EditText diastolica;
    EditText temperatura;
    EditText oxigenacion;
    EditText cardiaca;
    TextView diagnostico;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostico_medico);

        sistolica = (EditText) findViewById(R.id.sistolica);
        diastolica = (EditText) findViewById(R.id.diastolica);
        temperatura = (EditText) findViewById(R.id.temperatura);
        oxigenacion = (EditText) findViewById(R.id.oxigenacion);
        cardiaca = (EditText) findViewById(R.id.cardiaca);

        sistolica.setTransformationMethod(new NumericKeyBoardTtransformationMethod());
        diastolica.setTransformationMethod(new NumericKeyBoardTtransformationMethod());
        temperatura.setTransformationMethod(new NumericKeyBoardTtransformationMethod());
        oxigenacion.setTransformationMethod(new NumericKeyBoardTtransformationMethod());
        cardiaca.setTransformationMethod(new NumericKeyBoardTtransformationMethod());

        diagnostico = (TextView) findViewById(R.id.diagnostico);
        btn = (Button) findViewById(R.id.diagnosisbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDiagnostico();
            }
        });

    }

    public void startMainActivity(View view)
    {
        Intent intent  = new Intent(Diagnostico_medico.this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        finish();
    }

    public void startNewsActivity(View view)
    {
        Intent intent  = new Intent(Diagnostico_medico.this, Noticias.class);
        startActivity(intent);
        finish();
    }

    public void startContactActivity(View view)
    {
        Intent intent  = new Intent(Diagnostico_medico.this, Contactos.class);
        startActivity(intent);
        finish();
    }

    public void startMedicosActivity(View view)
    {
        Intent intent  = new Intent(Diagnostico_medico.this, Especialistas_medicos.class);
        startActivity(intent);
        finish();
    }

    public void startLudicasActivity(View view)
    {
        Intent intent  = new Intent(Diagnostico_medico.this, Actividades_ludicas.class);
        startActivity(intent);
        finish();
    }

    public void startDiagnosticoActivity(View view)
    {
        //Intent intent  = new Intent(Diagnostico_medico.this, Diagnostico_medico.class);
        //startActivity(intent);
        //finish();
    }

    public void getDiagnostico()
    {
        try {
            if(sistolica.getText().toString().isEmpty() || diastolica.getText().toString().isEmpty() || oxigenacion.getText().toString().isEmpty() || cardiaca.getText().toString().isEmpty()){
                Toast.makeText(Diagnostico_medico.this, "Por favor rellene todos los campos disponibles",Toast.LENGTH_LONG).show();
                diagnostico.setText("");
            }
            else {

                int pSistolica = Integer.parseInt(sistolica.getText().toString());
                int pDiastolica = Integer.parseInt(diastolica.getText().toString());
                int temperatura_corporal = Integer.parseInt(temperatura.getText().toString());
                int oxigeno = Integer.parseInt(oxigenacion.getText().toString());
                int fCardiaca = Integer.parseInt(cardiaca.getText().toString());

                String presion_aux = "";
                String temperatura_aux = "";
                String oxigeno_aux = "";
                String fCardiaca_aux = "";
                String diagnosticoFinal = "";

                if(pDiastolica >= 40 && pDiastolica <= 90)
                {
                    if(pSistolica >= 90 && pSistolica <= 140)
                    {
                        presion_aux = "Normal";
                    }
                    else if(pSistolica < 90)
                    {
                        presion_aux = "Baja";
                    }
                    else if(pSistolica > 140)
                    {
                        presion_aux = "Alta";
                    }
                }
                else if(pDiastolica < 40)
                {
                    if(pSistolica > 140)
                    {
                        presion_aux = "Alta";
                    }
                    else {
                        presion_aux = "Baja";
                    }
                }
                else if(pDiastolica > 90)
                {
                    presion_aux = "Alta";
                }

                if(temperatura_corporal >= 36 && temperatura_corporal <= 37)
                {
                    temperatura_aux = "Normal";
                }
                else if(temperatura_corporal < 36)
                {
                    temperatura_aux = "Baja";
                }
                else if(temperatura_corporal > 37)
                {
                    temperatura_aux = "Alta";
                }

                if(oxigeno >= 12 && oxigeno <= 20)
                {
                    oxigeno_aux = "Normal";
                }
                else if(oxigeno < 12)
                {
                    oxigeno_aux = "Baja";
                }
                else if(oxigeno > 20)
                {
                    oxigeno_aux = "Alta";
                }

                if(fCardiaca >= 60 && fCardiaca <= 90)
                {
                    fCardiaca_aux = "Normal";
                }
                else if(fCardiaca < 60)
                {
                    fCardiaca_aux = "Baja";
                }
                else if(fCardiaca > 90)
                {
                    fCardiaca_aux = "Alta";
                }

                if(presion_aux.equals("Normal") && temperatura_aux.equals("Normal") && oxigeno_aux.equals("Normal") && fCardiaca_aux.equals("Normal")){
                    diagnosticoFinal = "Usted se encuentra en buen estado de salud según las normativas internacionales de la OMS";
                }
                else{
                    diagnosticoFinal = "Es recomendable visitar al médico frente a cualquier irregularidad presentada en sus signos vitales";
                }

                String diagnosis = "El estado de la presión es: " + presion_aux + "\n \nEl estado de la temperatura corporal es: " + temperatura_aux + "\n \nEl estado de la frecuencia respiratioria es: " + oxigeno_aux + "\n \nEl estado de la frecuencia cardiaca es: " + fCardiaca_aux + "\n \n" + diagnosticoFinal;

                diagnostico.setText(diagnosis);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(Diagnostico_medico.this, "Se ha encontrado un valor no válido, por favor introduzca solo valores numéricos",Toast.LENGTH_LONG).show();
            diagnostico.setText("");
        }
    }

    private class NumericKeyBoardTtransformationMethod extends PasswordTransformationMethod
    {
        @Override
        public CharSequence getTransformation(CharSequence source, View view)
        {
            return source;
        }
    }
}