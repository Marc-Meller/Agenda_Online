package com.marcmeller.agenda_online.Perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.marcmeller.agenda_online.MenuPrincipal;
import com.marcmeller.agenda_online.R;

public class Perfil_Usuario extends AppCompatActivity {


    ImageView Imagen_Perfil;
    TextView Correo_Perfil, Uid_Perfil, Telefono_Perfil;
    EditText Nombres_Perfil, Apellidos_Perfil, Edad_Perfil,
            Domicilio_Perfil, Institucion_Perfil, Profesion_Perfil;

    ImageView Editar_Telefono;

    Button Guardar_Datos;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference Usuarios;

    Dialog dialog_establecer_telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        InicializarVariables();
        Editar_Telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Establecer_Telefono_Usuario();
            }
        });

    }

    private void InicializarVariables() {

        Imagen_Perfil = findViewById(R.id.Imagen_Perfil);

        Correo_Perfil = findViewById(R.id.Correo_Perfil);
        Uid_Perfil = findViewById(R.id.Uid_Perfil);
        Nombres_Perfil = findViewById(R.id.Nombres_Perfil);
        Apellidos_Perfil = findViewById(R.id.Apellidos_Perfil);
        Edad_Perfil = findViewById(R.id.Edad_Perfil);
        Telefono_Perfil = findViewById(R.id.Telefono_Perfil);
        Domicilio_Perfil = findViewById(R.id.Domicilio_Perfil);
        Institucion_Perfil = findViewById(R.id.Institucion_Perfil);
        Profesion_Perfil = findViewById(R.id.Profesion_Perfil);

        Editar_Telefono = findViewById(R.id.Editar_Telefono);

        dialog_establecer_telefono = new Dialog(Perfil_Usuario.this);

        Guardar_Datos = findViewById(R.id.Guardar_Datos);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        Usuarios = FirebaseDatabase.getInstance().getReference("Usuarios");

    }

    private void LecturaDeDatos() {
        Usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //Obtener sus Datos

                    String uid = ""+snapshot.child("uid").getValue();
                    String nombre = ""+snapshot.child("nombre").getValue();
                    String apellidos = ""+snapshot.child("apellidos").getValue();
                    String correo = ""+snapshot.child("correo").getValue();
                    String edad = ""+snapshot.child("edad").getValue();
                    String telefono = ""+snapshot.child("telefono").getValue();
                    String domicilio = ""+snapshot.child("domicilio").getValue();
                    String institucion = ""+snapshot.child("institucion").getValue();
                    String profesion = ""+snapshot.child("profesion").getValue();
                    String imagen_perfil = ""+snapshot.child("imagen_perfil").getValue();

                    //Seteo de datos
                    Uid_Perfil.setText(uid);
                    Nombres_Perfil.setText(nombre);
                    Apellidos_Perfil.setText(apellidos);
                    Correo_Perfil.setText(correo);
                    Edad_Perfil.setText(edad);
                    Telefono_Perfil.setText(telefono);
                    Domicilio_Perfil.setText(domicilio);
                    Institucion_Perfil.setText(institucion);
                    Profesion_Perfil.setText(profesion);

                    Cargar_Imagen(imagen_perfil);

                }

                Toast.makeText(Perfil_Usuario.this, "Esperando datos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Perfil_Usuario.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void Cargar_Imagen(String imagen_perfil) {
        try {
            //Se executa cuando la imagen ha sido traida desde firebase bien
            Glide.with(getApplicationContext()).load(imagen_perfil).placeholder(R.drawable.imagen_perfil_usuario).into(Imagen_Perfil);
        }catch (Exception e){
            //si falla se carga la predeterminada
            Glide.with(getApplicationContext()).load(R.drawable.imagen_perfil_usuario).into(Imagen_Perfil);
        }
    }

    private void Establecer_Telefono_Usuario(){
        CountryCodePicker ccp;
        EditText Establecer_Telefono;
        Button Btn_Aceptar_Telefono;


        dialog_establecer_telefono.setContentView(R.layout.cuadro_dialogo_establecer_telefono);

        ccp = dialog_establecer_telefono.findViewById(R.id.ccp);
        Establecer_Telefono = dialog_establecer_telefono.findViewById(R.id.Establecer_Telefono);
        Btn_Aceptar_Telefono = dialog_establecer_telefono.findViewById(R.id.Btn_Aceptar_Telefono);

        Btn_Aceptar_Telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo_pais = ccp.getSelectedCountryCodeWithPlus();
                String telefono = Establecer_Telefono.getText().toString();
                String codigo_pais_telefono = codigo_pais+telefono; //+52 99334000000

                if (!telefono.equals("")) {
                    Telefono_Perfil.setText(codigo_pais_telefono);
                    dialog_establecer_telefono.dismiss();
                }else{
                    Toast.makeText(Perfil_Usuario.this, "Ingrese su número telefonico", Toast.LENGTH_SHORT).show();
                    dialog_establecer_telefono.dismiss();
                }
            }
        });

        dialog_establecer_telefono.show();
        dialog_establecer_telefono.setCanceledOnTouchOutside(true);
    }

    private void ComprobarInicioSesion(){
        if (user!=null){
            LecturaDeDatos();
        }else {
            startActivity(new Intent(Perfil_Usuario.this, MenuPrincipal.class));
        }
    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }
}