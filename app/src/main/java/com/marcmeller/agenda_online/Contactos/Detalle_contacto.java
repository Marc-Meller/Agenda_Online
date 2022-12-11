package com.marcmeller.agenda_online.Contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcmeller.agenda_online.R;

public class Detalle_contacto extends AppCompatActivity {

    ImageView Imagen_C_D, Editar_imagen;

    TextView Id_C_D, Uid_Usuario_D, Nombres_C_D,Apellidos_C_D, Correo_C_D, Telefono_C_D, Fecha_Nacimiento_C_D,
            Edad_C_D, Institucion_C_D, Direccion_C_D;

    String id_c, uid_usuario, nombres_c, apellidos_c, correo_c, telefono_c, fecha_nacimiento_c, edad_c,
            institucion_c, direccion_c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        InicializarVariables();
        RecuperarDatosContacto();
        SetearDatosContacto();
    }

    private void InicializarVariables(){
        Imagen_C_D = findViewById(R.id.Imagen_C_D);
        Id_C_D = findViewById(R.id.Id_C_D);
        Uid_Usuario_D = findViewById(R.id.Uid_Usuario_D);
        Nombres_C_D = findViewById(R.id.Nombres_C_D);
        Apellidos_C_D = findViewById(R.id.Apellidos_C_D);
        Correo_C_D = findViewById(R.id.Correo_C_D);
        Telefono_C_D = findViewById(R.id.Telefono_C_D);
        Fecha_Nacimiento_C_D = findViewById(R.id.Fecha_Nacimiento_C_D);
        Edad_C_D = findViewById(R.id.Edad_C_D);
        Institucion_C_D = findViewById(R.id.Institucion_C_D);
        Direccion_C_D = findViewById(R.id.Direccion_C_D);

    }


    private void RecuperarDatosContacto(){
        Bundle bundle = getIntent().getExtras();

        id_c = bundle.getString("id_c");
        uid_usuario = bundle.getString("uid_usuario");
        nombres_c = bundle.getString("nombres_c");
        apellidos_c = bundle.getString("apellidos_c");
        correo_c = bundle.getString("correo_c");
        telefono_c = bundle.getString("telefono_c");
        fecha_nacimiento_c = bundle.getString("fecha_nacimiento_c");
        edad_c = bundle.getString("edad_c");
        institucion_c = bundle.getString("institucion_c");
        direccion_c = bundle.getString("direccion_c");
    }

    private void SetearDatosContacto(){

        Id_C_D.setText(id_c);
        Uid_Usuario_D.setText(uid_usuario);
        Nombres_C_D.setText(nombres_c);
        Apellidos_C_D.setText(apellidos_c);
        Correo_C_D.setText(correo_c);
        Telefono_C_D.setText(telefono_c);
        Fecha_Nacimiento_C_D.setText(fecha_nacimiento_c);
        Edad_C_D.setText(edad_c);
        Institucion_C_D.setText(institucion_c);
        Direccion_C_D.setText(direccion_c);

    }

}