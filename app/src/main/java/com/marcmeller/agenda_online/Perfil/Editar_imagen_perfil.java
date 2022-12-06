package com.marcmeller.agenda_online.Perfil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marcmeller.agenda_online.R;

public class Editar_imagen_perfil extends AppCompatActivity {

    ImageView ImagenPerfilAtualizada;
    Button BtnElegirImagenDe, BtnActualizarImagen;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_imagen_perfil);

        ImagenPerfilAtualizada = findViewById(R.id.ImagenPerfilAtualizada);
        BtnElegirImagenDe = findViewById(R.id.BtnElegirImagenDe);
        BtnActualizarImagen = findViewById(R.id.BtnActualizarImagen);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        BtnElegirImagenDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Editar_imagen_perfil.this, "Elegir imagen de", Toast.LENGTH_SHORT).show();
            }
        });

        BtnActualizarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Editar_imagen_perfil.this, "Actualizar imagen", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void LecturaDeImagen(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Obtener el dato imagen
                String imagen_perfil = ""+snapshot.child("imagen_perfil").getValue();


                Glide.with(getApplicationContext())
                        .load(imagen_perfil)
                        .placeholder(R.drawable.imagen_perfil_usuario)
                        .into(ImagenPerfilAtualizada);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}