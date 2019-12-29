package com.example.alkileov1.ActivityMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.ActivityDetalle.DetalleAlquilerActivity;
import com.example.alkileov1.ActivityDetalle.DetalleNotificacionActivity;
import com.example.alkileov1.Anadir.AnadirAlquilerActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.LoginActivity;
import com.example.alkileov1.Objetos.Alquiler;
import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.R;
import com.example.alkileov1.RecyclerView.RecyclerAdapter;
import com.example.alkileov1.RecyclerView.RecyclerAdapterAlquiler;
import com.example.alkileov1.RecyclerView.RecyclerAdapterFactura;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AlquileresActivity extends AppCompatActivity {
    ArrayList<Alquiler> arrayListAlquiler = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    int id;
    ImageButton anadirAlquiler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alquileres);

        anadirAlquiler = (ImageButton) findViewById(R.id.anadirAlquiler);


        setTitle("Gestiona tus alquileres");
        Bundle datos = getIntent().getExtras();
        id = datos.getInt("id");


        anadirAlquiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anadirAlquiler = new Intent(AlquileresActivity.this, AnadirAlquilerActivity.class);
                anadirAlquiler.putExtra("id_prop", id);
                startActivity(anadirAlquiler);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewAlquiler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        arrayListAlquiler = muestraAlquileres();
        adapter = new RecyclerAdapterAlquiler(getApplicationContext(),arrayListAlquiler );

        ((RecyclerAdapterAlquiler) adapter).setOnCLickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AlquileresActivity.this, "presionado", Toast.LENGTH_SHORT).show();

                String titulo = arrayListAlquiler.get(recyclerView.getChildAdapterPosition(v)).getTitulo();
                int id_alquiler = arrayListAlquiler.get(recyclerView.getChildAdapterPosition(v)).getId();
                int num_alq = arrayListAlquiler.get(recyclerView.getChildAdapterPosition(v)).getNum_alq();
                Intent detalleAlquiler= new Intent(AlquileresActivity.this, DetalleAlquilerActivity.class);
                detalleAlquiler.putExtra("titulo", titulo);
                detalleAlquiler.putExtra("id", id_alquiler);
                detalleAlquiler.putExtra("id_prop", id);
                detalleAlquiler.putExtra("num_alq", num_alq);
                startActivity(detalleAlquiler);

            }
        });

        recyclerView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        int ruta = R.menu.overflow_menu;
        getMenuInflater().inflate(ruta, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        menu.findItem(R.id.op1).setVisible(false);
        menu.findItem(R.id.op2).setVisible(false);
        menu.findItem(R.id.op3).setVisible(false);
        menu.findItem(R.id.op4).setVisible(false);
        menu.findItem(R.id.op5).setVisible(false);
        menu.findItem(R.id.op6).setVisible(false);
        menu.findItem(R.id.op7).setVisible(false);
        menu.findItem(R.id.op8).setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int Id = item.getItemId();

        if (Id == R.id.op9) {
            startActivity(new Intent(AlquileresActivity.this, Inicio2Activity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Alquiler> muestraAlquileres(){
        arrayListAlquiler.clear();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/consulta_alquileres.php?id=75";




        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                //Log.d("Test", "Test");
                //Toast.makeText(getApplicationContext(), "aqui3", Toast.LENGTH_SHORT).show();
                //int count = 0;


                try {
                    Log.d("Test1", "Test2");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Alquiler alquiler = new Alquiler();
                        alquiler.setTitulo(jsonObject.getString("Titulo"));
                        alquiler.setId(jsonObject.getInt("Id"));
                        alquiler.setNum_alq(jsonObject.getInt("Num_alq"));
                        alquiler.setId_prop(id);
                        arrayListAlquiler.add(alquiler);
                    }
                    //Toast.makeText(getApplicationContext(), "Consulta con exito", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(), "Error en la consulta", Toast.LENGTH_SHORT).show();
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        return arrayListAlquiler;


    }
}
