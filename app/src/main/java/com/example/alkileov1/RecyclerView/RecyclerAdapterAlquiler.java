package com.example.alkileov1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Objetos.Alquiler;
import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterAlquiler extends RecyclerView.Adapter<RecyclerAdapterAlquiler.MyViewHolder> implements View.OnClickListener {

    ArrayList<Alquiler> arrayList ;
    private View.OnClickListener listener;
    private Context context;


    public RecyclerAdapterAlquiler(Context context , ArrayList<Alquiler> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_alquiler,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName = holder.Titulo;




        textViewName.setText(arrayList.get(position).getTitulo());

        final int id = arrayList.get(position).getId();
        final int id_prop = arrayList.get(position).getId_prop();
        final int num_alq = arrayList.get(position).getNum_alq();

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

        TextView Titulo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo= (TextView) itemView.findViewById(R.id.titulo_alquiler);

        }
    }
}
