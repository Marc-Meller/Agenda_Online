package com.marcmeller.agenda_online.Contactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.marcmeller.agenda_online.Objetos.Contacto;
import com.marcmeller.agenda_online.R;
import com.marcmeller.agenda_online.ViewHolder.ViewHolderContacto;

public class Listar_Contactos extends AppCompatActivity {

    RecyclerView recyclerViewContactos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference BD_Usuarios;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    FirebaseRecyclerAdapter<Contacto, ViewHolderContacto> firebaseRecyclerAdapter;

    FirebaseRecyclerOptions<Contacto> firebaseRecyclerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contactos);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Contactos");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        recyclerViewContactos = findViewById(R.id.RecyclerViewContactos);
        recyclerViewContactos.setHasFixedSize(true);

        firebaseDatabase = FirebaseDatabase.getInstance();
        BD_Usuarios = firebaseDatabase.getReference("Usuarios");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        ListarContactos();

    }

    private void ListarContactos(){
        Query query = BD_Usuarios.child(user.getUid()).child("Contactos").orderByChild("nombres");
        firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Contacto>().setQuery(query, Contacto.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Contacto, ViewHolderContacto>(firebaseRecyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolderContacto viewHolderContacto, int position, @NonNull Contacto contacto) {
                viewHolderContacto.SetearDatosContacto(
                        getApplicationContext(),
                        contacto.getId_contacto(),
                        contacto.getUid_contacto(),
                        contacto.getNombres(),
                        contacto.getApellidos(),
                        contacto.getCorreo(),
                        contacto.getTelefono(),
                        contacto.getFec_nacimiento(),
                        contacto.getEdad(),
                        contacto.getInstitucion(),
                        contacto.getDireccion(),
                        contacto.getImagen()
                );

            }

            @NonNull
            @Override
            public ViewHolderContacto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
                ViewHolderContacto viewHolderContacto = new ViewHolderContacto(view);
                viewHolderContacto.setOnClickListener(new ViewHolderContacto.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Obteniendo los datos del Contacto seleccionado
                        String id_c = getItem(position).getId_contacto();
                        String uid_usuario = getItem(position).getUid_contacto();
                        String nombres_c = getItem(position).getNombres();
                        String apellidos_c = getItem(position).getApellidos();
                        String correo_c = getItem(position).getCorreo();
                        String telefono_c = getItem(position).getTelefono();
                        String fecha_nacimiento_c = getItem(position).getFec_nacimiento();
                        String edad_c = getItem(position).getEdad();
                        String institucion_c = getItem(position).getInstitucion();
                        String direccion_c = getItem(position).getDireccion();
                        String imagen_c = getItem(position).getImagen();

                        Intent intent = new Intent(Listar_Contactos.this, Detalle_contacto.class);
                        intent.putExtra("id_c", id_c);
                        intent.putExtra("uid_usuario", uid_usuario);
                        intent.putExtra("nombres_c", nombres_c);
                        intent.putExtra("apellidos_c",apellidos_c);
                        intent.putExtra("correo_c",correo_c);
                        intent.putExtra("telefono_c",telefono_c);
                        intent.putExtra("fecha_nacimiento_c",fecha_nacimiento_c);
                        intent.putExtra("edad_c",edad_c);
                        intent.putExtra("institucion_c",institucion_c);
                        intent.putExtra("direccion_c",direccion_c);
                        intent.putExtra("imagen_c",imagen_c);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(Listar_Contactos.this, "On item long click", Toast.LENGTH_SHORT).show();
                    }
                });
                return viewHolderContacto;
            }
        };

        recyclerViewContactos.setLayoutManager(new GridLayoutManager(Listar_Contactos.this, 2));
        firebaseRecyclerAdapter.startListening();
        recyclerViewContactos.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter!=null){
            firebaseRecyclerAdapter.startListening();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_agregar_contacto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.Agregar_contacto){
            //Recuperamos el Uid
            String Uid_Recuperado = getIntent().getStringExtra("Uid");
            Intent intent = new Intent(Listar_Contactos.this, Agregar_Contacto.class);
            //Enviamos el dato Uid
            intent.putExtra("Uid", Uid_Recuperado);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}