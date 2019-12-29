package com.example.alkileov1.Anadir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.R;

import java.util.HashMap;
import java.util.Map;

public class AnadirUsuarioActivity extends AppCompatActivity {

    EditText editTextMensaje;
    ImageButton btnVolver;
    Button  btnEnviar;
    TextView textoNombre;

    int id, id_usuario;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_usuario);

        editTextMensaje = (EditText) findViewById(R.id.comentario_peticion);
        btnEnviar = (Button) findViewById(R.id.btn_enviar_peticion);
        btnVolver = (ImageButton) findViewById(R.id.btn_cerrar_popUp);
        textoNombre = (TextView) findViewById(R.id.textoNombre);

        Bundle datos = getIntent().getExtras();
        id = datos.getInt("id");
        id_usuario = datos.getInt("id_usuario");
        nombre = datos.getString("nombre");

        textoNombre.setText("Contacta con "+nombre);


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(AnadirUsuarioActivity.this, AnadirAlquilerActivity.class);
                volver.putExtra("id_prop", id);
                startActivity(volver);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String mensaje = editTextMensaje.getText().toString();
                ejecutarServicio("https://carlosgimeno.es/alkileo/ANDROID/api/v1/insertar_peticion.php");

            }
        });
    }

    private void ejecutarServicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Petición Enviada", Toast.LENGTH_SHORT).show();
                Intent volver = new Intent(AnadirUsuarioActivity.this, AnadirAlquilerActivity.class);
                volver.putExtra("id_prop", id);
                startActivity(volver);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                //foto = convertirFoto(bitmap);

                parametros.put("id_prop", String.valueOf(id));
                parametros.put("id_inq", String.valueOf(id_usuario));
                parametros.put("num_alq", String.valueOf(1));
                parametros.put("estado", String.valueOf(0));
                parametros.put("comentario",editTextMensaje.getText().toString());

                return parametros;
            }
        };

        //Toast.makeText(this, foto, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
