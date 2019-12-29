package com.example.alkileov1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Objetos.Alquiler;
import com.example.alkileov1.Objetos.Mensaje;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterMensaje extends RecyclerView.Adapter<RecyclerAdapterMensaje.MyViewHolder> implements View.OnClickListener {

    ArrayList<Mensaje> arrayList ;
    private View.OnClickListener listener;
    private Context context;


    public RecyclerAdapterMensaje(Context context , ArrayList<Mensaje> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_mensaje,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewTitulo = holder.Titulo;
        TextView textViewDescrpcion = holder.Descripcion;




        textViewTitulo.setText(arrayList.get(position).getTitulo());
        textViewDescrpcion.setText(arrayList.get(position).getDescripcion());




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnCLickListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Titulo, Descripcion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo= (TextView) itemView.findViewById(R.id.titulo_mensaje);
            Descripcion = (TextView) itemView.findViewById(R.id.descripcion_mensaje);

        }
    }
}
