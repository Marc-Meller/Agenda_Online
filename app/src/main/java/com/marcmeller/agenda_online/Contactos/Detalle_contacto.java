package com.marcmeller.agenda_online.Contactos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marcmeller.agenda_online.R;

public class Detalle_contacto extends AppCompatActivity {

    ImageView Imagen_C_D, Editar_imagen;

    TextView Id_C_D, Uid_Usuario_D, Nombres_C_D,Apellidos_C_D, Correo_C_D, Telefono_C_D, Fecha_Nacimiento_C_D,
            Edad_C_D, Institucion_C_D, Direccion_C_D;

    String id_c, uid_usuario, nombres_c, apellidos_c, correo_c, telefono_c, fecha_nacimiento_c, edad_c,
            institucion_c, direccion_c;

    Button Llamar_C, Mensaje_C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Detalle contacto");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        InicializarVariables();
        RecuperarDatosContacto();
        SetearDatosContacto();
        ObtenerImagen();
        
        Llamar_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Detalle_contacto.this, Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED){
                    LlamarContacto();
                }else{
                    SolicitudPermisoLlamada.launch(Manifest.permission.CALL_PHONE);
                }
            }
        });
        
        Mensaje_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Detalle_contacto.this, Manifest.permission.SEND_SMS) ==
                        PackageManager.PERMISSION_GRANTED){
                    EnviarMensaje();
                }else{
                    SolicitudPermisoMensaje.launch(Manifest.permission.SEND_SMS);
                }
            }
        });
        
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

        Llamar_C = findViewById(R.id.Llamar_C);
        Mensaje_C = findViewById(R.id.Mensaje_C);

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
        Nombres_C_D.setText("Nombres: "+nombres_c);
        Apellidos_C_D.setText("Apellidos: "+apellidos_c);
        Correo_C_D.setText("Correo: "+correo_c);
        Telefono_C_D.setText(telefono_c);
        Fecha_Nacimiento_C_D.setText("Cumpleaños: "+fecha_nacimiento_c);
        Edad_C_D.setText("Edad: "+edad_c);
        Institucion_C_D.setText("Institucion: "+institucion_c);
        Direccion_C_D.setText("Direccion: "+direccion_c);

    }

    private void ObtenerImagen(){
        String imagen = getIntent().getStringExtra("imagen_c");

        try {
            Glide.with(getApplicationContext()).load(imagen).placeholder(R.drawable.imagen_contacto).into(Imagen_C_D);
        }catch (Exception e){
            Toast.makeText(this, "Esperando imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void LlamarContacto(){
        String telefono = Telefono_C_D.getText().toString();
        if (!telefono.equals("")){
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+telefono));
            startActivity(intent);
        }else{
            Toast.makeText(this, "Sin número telefonico", Toast.LENGTH_SHORT).show();
        }
    }

    private ActivityResultLauncher<String> SolicitudPermisoLlamada = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), isGrated -> {
                if (isGrated){
                 LlamarContacto();
                }else{
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                }
            });


    private void EnviarMensaje(){
        String telefono = Telefono_C_D.getText().toString();
        if (!telefono.equals("")){
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:"+telefono));
            intent.putExtra("sms_body", "");
            startActivity(intent);
        }else{
            Toast.makeText(this, "Sin número telefonico", Toast.LENGTH_SHORT).show();
        }
    }

    private ActivityResultLauncher<String> SolicitudPermisoMensaje = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(), isGrated -> {
                if (isGrated){
                    EnviarMensaje();
                }else{
                    Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
                }
            });


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}