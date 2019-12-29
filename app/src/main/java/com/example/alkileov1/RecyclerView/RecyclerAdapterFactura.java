package com.example.alkileov1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alkileov1.Objetos.Factura;
import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.R;

import java.util.ArrayList;

public class RecyclerAdapterFactura extends RecyclerView.Adapter<RecyclerAdapterFactura.MyViewHolder> {

    ArrayList<Factura> arrayList ;
    private Context context;


    public RecyclerAdapterFactura(Context context , ArrayList<Factura> arrayList){
        this.context = context ;
        this.arrayList=arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_factura,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName = holder.Concepto;
        TextView textViewEmail = holder.Importe;
        TextView textViewId = holder.Id;

        Button pagar = holder.Pagar;


        textViewId.setText(String.valueOf(arrayList.get(position).getId()));
        textViewName.setText(arrayList.get(position).getConcepto());
        textViewEmail.setText(arrayList.get(position).getImporte()+" €");

        final String id = String.valueOf(arrayList.get(position).getId());

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Concepto, Importe, Id;
        Button Pagar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Concepto= (TextView) itemView.findViewById(R.id.concepto_factura);
            Importe= (TextView) itemView.findViewById(R.id.importe_factura);
            Id = (TextView) itemView.findViewById(R.id.id_factura);
            Pagar = (Button) itemView.findViewById(R.id.btn_pagar_factura);
        }
    }
}
