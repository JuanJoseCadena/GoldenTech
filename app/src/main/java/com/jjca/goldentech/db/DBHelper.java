package com.jjca.goldentech.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GoldenTech.db";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE `app_ratings` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `rate` int(11) NOT NULL,\n" +
                "  `date` timestamp NOT NULL,\n" +
                "  `user` int(11) NOT NULL\n" +
                ");");

        db.execSQL("CREATE TABLE `clinical_diagnosis` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `diagnosis_type` varchar(200) NOT NULL,\n" +
                "  `min_value` int(11) NOT NULL,\n" +
                "  `max_value` int(11) NOT NULL,\n" +
                "  `diagnosis_result` varchar(20) NOT NULL,\n" +
                "  `diagnosis_for` int(11) NOT NULL,\n" +
                "  `diagnosis_date` timestamp NOT NULL,\n" +
                "  `note` text NOT NULL\n" +
                ");");

        db.execSQL("CREATE TABLE `contact_info` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `name` text NOT NULL,\n" +
                "  `phone` varchar(12) NOT NULL,\n" +
                "  `gender` text NOT NULL,\n" +
                "  `user_contact` int(11) NOT NULL \n" +
                ");");

        db.execSQL("CREATE TABLE `medical_contact` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `name` text NOT NULL,\n" +
                "  `phone` varchar(12) NOT NULL,\n" +
                "  `gender` text NOT NULL,\n" +
                "  `medical_degree` text NOT NULL\n" +
                ")");

        db.execSQL("INSERT INTO `medical_contact` (`id`, `name`, `phone`, `gender`, `medical_degree`) VALUES\n" +
                "(1, 'Santiago Moreno', '3185484596', 'Masculino', 'Médico familiar'),\n" +
                "(2, 'Mayra Vanegas', '3009920439', 'Femenino', 'Médico familiar'),\n" +
                "(3, 'Andres Felipe Camacho', '3028305515', 'Masculino', 'Geriatra'),\n" +
                "(4, 'Mauricio Andrés Uribe', '3160508333', 'Masculino', 'Geriatra'),\n" +
                "(5, 'María del Pilar Peña', '3175300912', 'Femenino', 'Cardióloga'),\n" +
                "(6, 'Isabel Cristina Cardenas', '3176360864', 'Femenino', 'Cardióloga'),\n" +
                "(7, 'Hector Julian Cubillos', '3144715127', 'Masculino', 'Internista'),\n" +
                "(8, 'Karen Camacho', '3002733751', 'Femenino', 'Internista'),\n" +
                "(9, 'Edwin Quintero', '3175027213', 'Masculino', 'Nefrólogo'),\n" +
                "(10, 'Mario León García', '3003175553', 'Masculino', 'Neumólogo'),\n" +
                "(11, 'Claudio Aguirre Castañeda', '3053176543', 'Masculino', 'Neurólogo');");

        db.execSQL("CREATE TABLE `news_sections` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `news_section` varchar(100) NOT NULL,\n" +
                "  `access_point` text NOT NULL\n" +
                ");");

        db.execSQL("INSERT INTO `news_sections` (`id`, `news_section`, `access_point`) VALUES\n" +
                "(1, 'Deportes', '');");

        db.execSQL("CREATE TABLE `play_activities` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `activity_name` text NOT NULL,\n" +
                "  `activity_type` text DEFAULT NULL,\n" +
                "  `category` text DEFAULT NULL,\n" +
                "  `connection_point` text NOT NULL,\n" +
                "  `used_by` int(11) NOT NULL,\n" +
                "  `times_solved` int(11) NOT NULL \n" +
                ");");

        db.execSQL("INSERT INTO `play_activities` (`id`, `activity_name`, `activity_type`, `category`, `connection_point`, `used_by`, `times_solved`) VALUES\n" +
                "(1, 'Colonia', 'Sopa De Letras', 'Historia', 'https://es.educaplay.com/recursos-educativos/6554039-colonia.html', 1, 0),\n" +
                "(2, 'Diccionario', 'Crucigrama', 'Vocabulario', 'https://es.educaplay.com/recursos-educativos/10117651-diccionario.html', 1, 0),\n" +
                "(3, 'Enfermedades', 'Sopa De Letras', 'Salud', 'https://es.educaplay.com/recursos-educativos/15275109-enfermedades.html', 1, 0),\n" +
                "(4, 'Imagen Y Concepto', 'Memoria', 'Aplicaciones Tecnológicas', 'https://es.educaplay.com/recursos-educativos/13134059-relacionar_imagen_y_concepto.html', 1, 0),\n" +
                "(5, 'Relaciona Cada Ícono', 'Asociación O Relación', 'Aplicaciones Tecnológicas', 'https://es.educaplay.com/recursos-educativos/13133352-relaciona_cada_icono.html', 1, 0),\n" +
                "(6, 'Ordenar Letras', 'Destreza', 'Tecnología', 'https://es.educaplay.com/recursos-educativos/13042554-ordenar_letras.html', 1, 0),\n" +
                "(7, '#Yovaloro', 'Destreza Y Conocimiento', 'Tercera Edad', 'https://es.educaplay.com/recursos-educativos/5501931-yovaloro.html', 1, 0),\n" +
                "(8, 'Mayores Unidos Por El Ambiente', 'Sopa De Letras', 'Medio Ambiente', 'https://es.educaplay.com/recursos-educativos/6398521-mayores_unidos_por_el_ambiente.html', 1, 0),\n" +
                "(9, 'Actividad Fisica', 'Sopa De Letras', 'Ejercicio', 'https://es.educaplay.com/recursos-educativos/2713849-actividad_fisica.html', 1, 0),\n" +
                "(10, 'Salud Fisica Y Mental', 'Memoria', 'Salud', 'https://es.educaplay.com/recursos-educativos/10149266-salud_fisica_y_mental.html', 1, 0),\n" +
                "(11, 'Tarjeta De Obstáculos', 'Adivinanza', 'Regiones', 'https://es.educaplay.com/recursos-educativos/11504314-tarjeta_de_obstaculos_3.html', 1, 0),\n" +
                "(12, 'Música', 'Destreza Y Conocimiento', 'Elementos Musicales', 'https://es.educaplay.com/recursos-educativos/6738091-musica.html', 1, 0),\n" +
                "(13, 'Reconociendo Sonidos', 'Memoria', 'Música', 'https://es.educaplay.com/recursos-educativos/5568294-reconociendo_sonidos.html', 1, 0),\n" +
                "(14, 'Obras Literarias', 'Crucigrama', 'Literatura', 'https://es.educaplay.com/recursos-educativos/1683526-obras_literarias.html', 1, 0),\n" +
                "(15, 'Paises Del Mundo', 'Crucigrama', 'Geografía', 'https://es.educaplay.com/recursos-educativos/13134550-paises_del_mundo.html', 1, 0),\n" +
                "(16, 'Cultura Ciudadana', 'Crucigrama', 'Cívica', 'https://es.educaplay.com/recursos-educativos/3669516-cultura_ciudadana.html', 1, 0),\n" +
                "(17, 'Rios De Colombia', 'Sopa De Letras', 'Geografia', 'https://es.educaplay.com/recursos-educativos/4782331-rios_de_colombia.html', 1, 0),\n" +
                "(18, 'Adulto Mayor', 'Sopa De Letras', 'Salud', 'https://es.educaplay.com/recursos-educativos/7202348-cancer_de_prostata.html', 1, 0);");

        db.execSQL("CREATE TABLE `sections` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `section_name` varchar(50) NOT NULL,\n" +
                "  `connection_point` text NOT NULL\n" +
                ");");

        db.execSQL("INSERT INTO `sections` (`id`, `section_name`, `connection_point`) VALUES\n" +
                "(1, 'Noticias', '');");

        db.execSQL("CREATE TABLE `users` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `last_login` timestamp NOT NULL,\n" +
                "  `last_section_used` int(11) NOT NULL,\n" +
                "  `news_preferences` int(11) NOT NULL\n" +
                ");");

        db.execSQL("INSERT INTO `users` (`id`, `last_login`, `last_section_used`, `news_preferences`) VALUES\n" +
                "(1, '2023-07-28 17:11:06', 1, 1),\n" +
                "(2, '2023-07-28 17:22:11', 1, 1),\n" +
                "(3, '2023-07-28 17:24:14', 1, 1),\n" +
                "(4, '2023-07-28 17:24:14', 1, 1),\n" +
                "(5, '2023-07-28 17:24:15', 1, 1),\n" +
                "(6, '2023-07-28 17:24:15', 1, 1),\n" +
                "(7, '2023-07-28 17:24:15', 1, 1),\n" +
                "(8, '2023-07-28 17:24:15', 1, 1),\n" +
                "(9, '2023-07-28 17:24:15', 1, 1),\n" +
                "(10, '2023-07-28 17:24:15', 1, 1),\n" +
                "(11, '2023-07-28 17:24:15', 1, 1),\n" +
                "(12, '2023-07-28 17:24:15', 1, 1),\n" +
                "(13, '2023-07-28 17:24:15', 1, 1),\n" +
                "(14, '2023-07-28 17:24:15', 1, 1),\n" +
                "(15, '2023-07-28 17:24:15', 1, 1),\n" +
                "(16, '2023-07-28 17:24:39', 1, 1),\n" +
                "(17, '2023-07-28 17:24:39', 1, 1),\n" +
                "(18, '2023-07-28 17:24:40', 1, 1);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
