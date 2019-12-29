package com.example.alkileov1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.Objetos.Factura;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterDocumento extends RecyclerView.Adapter<RecyclerAdapterDocumento.MyViewHolder> {

    ArrayList<Documento> arrayList ;
    private Context context;


    public RecyclerAdapterDocumento(Context context , ArrayList<Documento> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_documento,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName = holder.Titulo;
        TextView textViewId = holder.Id;
        Button botonBorrar = holder.Borrar;



        textViewName.setText(arrayList.get(position).getTitulo());
        textViewId.setText(String.valueOf(arrayList.get(position).getId()));

        final int id = arrayList.get(position).getId();

        /*botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
            }
        });*/



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Titulo, Archivo, Id;
        Button Borrar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo= (TextView) itemView.findViewById(R.id.titulo_documento);
            Id = (TextView) itemView.findViewById(R.id.id_documento);
            //Borrar = (ImageButton) itemView.findViewById(R.id.btn_borrar_documento);

        }
    }
}
