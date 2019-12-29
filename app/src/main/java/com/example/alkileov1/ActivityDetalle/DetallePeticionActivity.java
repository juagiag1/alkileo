package com.example.alkileov1.ActivityDetalle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.Objetos.Peticion;
import com.example.alkileov1.R;
import com.example.alkileov1.RecyclerView.RecyclerAdapterMensaje;
import com.example.alkileov1.RecyclerView.RecyclerAdapterPeticion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetallePeticionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Peticion> arrayListPeticion = new ArrayList<>();
    int id;
    String estado;

    Button btnAceptar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_peticion);

        Bundle datos = getIntent().getExtras();
        if (datos!=null){

            id = datos.getInt("id");

        }

        final Intent volver = new Intent(DetallePeticionActivity.this, Inicio2Activity.class);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPeticion);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        arrayListPeticion.clear();

        arrayListPeticion = muestraPeticion();
        adapter = new RecyclerAdapterPeticion(getApplicationContext(),arrayListPeticion );
        recyclerView.setAdapter(adapter);

        btnAceptar = (Button) findViewById(R.id.btn_aceptar_peticion);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar_peticion);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado = "1";
                id = arrayListPeticion.get(0).getId();
                //Toast.makeText(DetallePeticionActivity.this, String.valueOf(id)+","+estado, Toast.LENGTH_SHORT).show();
                actualizarPeticion();
                Toast.makeText(getApplicationContext(), "Has aceptado la petición", Toast.LENGTH_SHORT).show();
                startActivity(volver);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado = "2";
                id = arrayListPeticion.get(0).getId();
                //Toast.makeText(DetallePeticionActivity.this, String.valueOf(id)+","+estado, Toast.LENGTH_SHORT).show();
                actualizarPeticion();
                Toast.makeText(getApplicationContext(), "Has cancelado la petición", Toast.LENGTH_SHORT).show();
                startActivity(volver);
            }
        });
    }

    private ArrayList<Peticion> muestraPeticion(){

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/consulta_peticion.php?id="+id;




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
                        Peticion peticion = new Peticion();
                        peticion.setComentario(jsonObject.getString("Comentario"));
                        peticion.setNombre_prop(jsonObject.getString("Nombre_prop"));
                        peticion.setEstado(jsonObject.getInt("Estado"));
                        peticion.setId(jsonObject.getInt("Id"));
                        arrayListPeticion.add(peticion);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

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


        return arrayListPeticion;


    }

    private void actualizarPeticion(){
        String URL = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/actualizar_peticion.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();

                parametros.put("id", String.valueOf(id));
                parametros.put("estado", estado);



                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
