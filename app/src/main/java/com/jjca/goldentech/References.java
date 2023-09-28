package com.jjca.goldentech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class References extends AppCompatActivity {

    TextView icons;
    TextView plays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        icons = (TextView) findViewById(R.id.icon_references);
        plays = (TextView) findViewById(R.id.play_references);

        String iconsText = "Srip - https://www.flaticon.com/free-icon/stethoscope_1235308\n" +
                "\n" +
                "Good Ware - https://www.flaticon.com/free-icon/first-aid-kit_862032\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/phone_1230231\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/phone_1230180\n" +
                "\n" +
                "Pixel Perfect - https://www.flaticon.com/free-icon/newspaper_1246942\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/cubes_405143\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/cubes_404991\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/menu-button-of-three-horizontal-lines_56763\n" +
                "\n" +
                "Afif Fudin - https://www.flaticon.com/free-icon/delete_11502829\n" +
                "\n" +
                "Kmg Design - https://www.flaticon.com/free-icon/photo_4904233\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/user_3237472\n" +
                "\n" +
                "Icon Pond - https://www.flaticon.com/free-icon/doctor_387561\n" +
                "\n" +
                "Icon Pond - https://www.flaticon.com/free-icon/doctor_387569\n" +
                "\n" +
                "Lakonicon - https://www.flaticon.com/free-icon/phone-call_9946341\n" +
                "\n" +
                "IYAHICON - https://www.flaticon.com/free-icon/trash-bin_5028066\n" +
                "\n" +
                "IconKanan - https://www.flaticon.com/free-icon/info_5285229\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/crossword_3813765\n" +
                "\n" +
                "Flat Icons - https://www.flaticon.com/free-icon/memory_2247779\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/matching_10257147\n" +
                "\n" +
                "Eucalyp - https://www.flaticon.com/free-icon/competence_2631384\n" +
                "\n" +
                "Fragneel - https://www.flaticon.com/free-icon/logical-thinking_9192463\n" +
                "\n" +
                "Freepik - https://www.flaticon.com/free-icon/puzzle_2500010\n" +
                "\n" +
                "Flat Icons - https://www.flaticon.com/free-icon/word_2247872\n" +
                "\n" +
                "Lexica - https://lexica.art/\n";

        String playText = "Clara Nancy Villamil Duarte - https://es.educaplay.com/recursos-educativos/6554039-colonia.html\n" +
                "\n" +
                "DIEGO ALEXANDER ARIAS PRIETO - https://es.educaplay.com/recursos-educativos/10117651-diccionario.html\n" +
                "\n" +
                "Felipe Angulo - https://es.educaplay.com/recursos-educativos/15275109-enfermedades.html\n" +
                "\n" +
                "JORGE MARIO MEDINA MORALES - https://es.educaplay.com/recursos-educativos/13134059-relacionar_imagen_y_concepto.html\n" +
                "\n" +
                "JORGE MARIO MEDINA MORALES - https://es.educaplay.com/recursos-educativos/13133352-relaciona_cada_icono.html\n" +
                "\n" +
                "JORGE MARIO MEDINA MORALES - https://es.educaplay.com/recursos-educativos/13042554-ordenar_letras.html\n" +
                "\n" +
                "Alvaro Mantilla - https://es.educaplay.com/recursos-educativos/5501931-yovaloro.html\n" +
                "\n" +
                "NICOLAS OLAYA CHIRIVI - https://es.educaplay.com/recursos-educativos/6398521-mayores_unidos_por_el_ambiente.html\n" +
                "\n" +
                "Tatiana Ramirez - https://es.educaplay.com/recursos-educativos/2713849-actividad_fisica.html\n" +
                "\n" +
                "Johanna Milena Rodriguez - https://es.educaplay.com/recursos-educativos/10149266-salud_fisica_y_mental.html\n" +
                "\n" +
                "María Karina Ramos - https://es.educaplay.com/recursos-educativos/11504314-tarjeta_de_obstaculos_3.html\n" +
                "\n" +
                "Jonathan Eduardo Acero Vargas - https://es.educaplay.com/recursos-educativos/6738091-musica.html\n" +
                "\n" +
                "Johanna Amaya Conejo - https://es.educaplay.com/recursos-educativos/5568294-reconociendo_sonidos.html\n" +
                "\n" +
                "Edilma Lucia Restrepo Arenas - https://es.educaplay.com/recursos-educativos/1683526-obras_literarias.html\n" +
                "\n" +
                "DAVID CAMILO VERGARA SANABRIA - https://es.educaplay.com/recursos-educativos/13134550-paises_del_mundo.html\n" +
                "\n" +
                "Joiner Jimenez Santos - https://es.educaplay.com/recursos-educativos/3669516-cultura_ciudadana.html\n" +
                "\n" +
                "Orlando Caicedo - https://es.educaplay.com/recursos-educativos/4782331-rios_de_colombia.html\n" +
                "\n" +
                "Sandra Neira - https://es.educaplay.com/recursos-educativos/7202348-cancer_de_prostata.html\n";

        icons.setText(iconsText);
        plays.setText(playText);
    }
}