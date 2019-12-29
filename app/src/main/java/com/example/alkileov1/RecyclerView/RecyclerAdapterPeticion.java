package com.example.alkileov1.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.Objetos.Peticion;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterPeticion extends RecyclerView.Adapter<RecyclerAdapterPeticion.MyViewHolder> {

    ArrayList<Peticion> arrayList ;
    private Context context;


    public RecyclerAdapterPeticion(Context context , ArrayList<Peticion> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_peticion,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewTitulo = holder.Titulo;
        TextView textViewDescripcion = holder.Descripcion;
        TextView textViewEstado = holder.Estado;
        





        textViewTitulo.setText(arrayList.get(position).getNombre_prop());
        textViewDescripcion.setText(arrayList.get(position).getComentario());
        textViewEstado.setText(String.valueOf(arrayList.get(position).getEstado()));








    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Titulo, Descripcion, Estado;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo= (TextView) itemView.findViewById(R.id.titulo_peticion);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion_peticion);
            Estado = (TextView) itemView.findViewById(R.id.estado_peticion);
            


        }
    }
}
