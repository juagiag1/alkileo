package com.example.alkileov1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> implements View.OnClickListener{

    ArrayList<Notificacion> arrayList ;
    private View.OnClickListener listener;
    private Context context;


    public RecyclerAdapter(Context context , ArrayList<Notificacion> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_notificacion,parent, false);

        view.setOnClickListener(this);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName = holder.Titulo;
        TextView textViewEmail = holder.Descripcion;
        ImageView imageViewFoto = holder.Foto;
        int id = holder.id;

        textViewName.setText(arrayList.get(position).getTitulo());
        textViewEmail.setText(arrayList.get(position).getDescripcion());
        imageViewFoto.setImageBitmap(arrayList.get(position).getFoto());

        id = arrayList.get(position).getId();


        //holder.Name.setText(arrayList.get(position).getName());
        //holder.Email.setText(arrayList.get(position).getEmail());

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
        ImageView Foto;
        public int id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Titulo= (TextView) itemView.findViewById(R.id.titulo_notificacion);
            Descripcion= (TextView) itemView.findViewById(R.id.descripcion_notificacion);
            Foto = (ImageView) itemView.findViewById(R.id.foto_notificacion);
        }
    }
}
