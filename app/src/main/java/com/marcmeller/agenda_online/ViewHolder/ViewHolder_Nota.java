package com.marcmeller.agenda_online.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marcmeller.agenda_online.R;

public class ViewHolder_Nota extends RecyclerView.ViewHolder {

    View mView;

    private ViewHolder_Nota.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position); //Se ejecuta al precionar en el Item
        void onItemLongClick(View view, int position); //Se ejecuta al mantener presionado el Item
    }

    public void setOnClickListerer(ViewHolder_Nota.ClickListener clickListerer){
        mClickListener = clickListerer;
    }

    public ViewHolder_Nota(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return false;
            }
        });

    }

    public void SetearDatos(Context context, String id_nota, String uid_usuario, String correo_usuario,
                            String fechas_hora_registro, String titulo, String descripcion,
                            String fecha_nota, String estado){
        //Declarar vistas
        TextView Id_nota_Item, Uid_Usuario_Item, Correo_Usuario_Item, Fecha_hora_regitro_Item,
                Titulo_Item, Descripcion_Item, Fecha_Item, Estado_Item;

        //Establecer la conexion con el Item
        Id_nota_Item = mView.findViewById(R.id.Id_nota_Item);
        Uid_Usuario_Item = mView.findViewById(R.id.Uid_Usuario_Item);
        Correo_Usuario_Item = mView.findViewById(R.id.Correo_Usuario_Item);
        Fecha_hora_regitro_Item = mView.findViewById(R.id.Fecha_hora_regitro_Item);
        Titulo_Item = mView.findViewById(R.id.Titulo_Item);
        Descripcion_Item = mView.findViewById(R.id.Descripcion_Item);
        Fecha_Item = mView.findViewById(R.id.Fecha_Item);
        Estado_Item = mView.findViewById(R.id.Estado_Item);

        //Setear la info dentro del Item
        Id_nota_Item.setText(id_nota);
        Uid_Usuario_Item.setText(uid_usuario);
        Correo_Usuario_Item.setText(correo_usuario);
        Fecha_hora_regitro_Item.setText(fechas_hora_registro);
        Titulo_Item.setText(titulo);
        Descripcion_Item.setText(descripcion);
        Fecha_Item.setText(fecha_nota);
        Estado_Item.setText(estado);
    }

}