package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register3Activity extends AppCompatActivity {
    String rol, trato, nombre, apellidos;
    EditText email, password, confirmPassword;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);


        Bundle datos = this.getIntent().getExtras();
        rol = datos.getString("rol");
        trato = datos.getString("trato");
        nombre = datos.getString("nombre");
        apellidos = datos.getString("apellidos");

        setTitle(nombre+", es el último paso");

        email = (EditText) findViewById(R.id.registroEmail);
        password = (EditText) findViewById(R.id.registroPassword);
        confirmPassword = (EditText) findViewById(R.id.registroConfirmPassword);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrarse);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("https://carlosgimeno.es/alkileo/ANDROID/api/v1/insertar_usuario.php");

            }
        });


    }



    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register3Activity.this, ConfirmacionActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("trato", trato);
                startActivity(intent);
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
                parametros.put("rol", rol);
                parametros.put("trato", trato);
                parametros.put("nombre", nombre);
                parametros.put("apellidos", apellidos);
                parametros.put("email", email.getText().toString());
                parametros.put("password", password.getText().toString());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
