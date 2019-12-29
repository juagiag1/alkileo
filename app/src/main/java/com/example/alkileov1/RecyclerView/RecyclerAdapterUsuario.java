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
import com.example.alkileov1.Objetos.Usuario;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterUsuario extends RecyclerView.Adapter<RecyclerAdapterUsuario.MyViewHolder> implements View.OnClickListener{

    ArrayList<Usuario> arrayList ;
    private View.OnClickListener listener;
    private Context context;


    public RecyclerAdapterUsuario(Context context , ArrayList<Usuario> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_usuario,parent, false);

        view.setOnClickListener(this);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName = holder.Nombre;
        TextView textViewRol = holder.Rol;


        textViewName.setText(arrayList.get(position).getNombre()+" "+arrayList.get(position).getApellidos());

        String rol = arrayList.get(position).getRol();

        if (rol.equals("INQ")){
            textViewRol.setText("Inquilino");
        }else if(rol.equals("PRO")){
            textViewRol.setText("Propietario");
        }else {
            textViewRol.setText("");
        }


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

        TextView Nombre, Rol;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Nombre= (TextView) itemView.findViewById(R.id.nombre_usuario);
            Rol= (TextView) itemView.findViewById(R.id.rol_usuario);
        }
    }
}
