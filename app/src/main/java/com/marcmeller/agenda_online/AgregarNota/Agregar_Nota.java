package com.marcmeller.agenda_online.AgregarNota;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.marcmeller.agenda_online.R;


public class Agregar_Nota extends AppCompatActivity {

    TextView Uid_Usuario, Correo_Usuario, Fecha_hora_actual, Fecha, Estado;

    EditText Titulo, Descripcion;

    Button Btn_calenadrio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_nota);

        InicializarVariables();



    }

    private void InicializarVariables(){

        Uid_Usuario = findViewById(R.id.Uid_Usuario);
        Correo_Usuario = findViewById(R.id.Correo_Usuario);
        Fecha_hora_actual = findViewById(R.id.Fecha_hora_actual);
        Fecha = findViewById(R.id.Fecha);
        Estado = findViewById(R.id.Estado);

        Titulo = findViewById(R.id.Titulo);
        Descripcion = findViewById(R.id.Descripcion);
        Btn_calenadrio = findViewById(R.id.Btn_calenadrio);
    }
}