package com.example.alkileov1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class _LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText campoEmail, campoPassword;

    Button btnLogin, btnRegister1;
    private Toolbar toolbar;
    TextView txtRespuesta;
    Boolean resultado;
    ArrayList<Boolean> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Bienvenido");

        btnRegister1 = (Button) findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtRespuesta = (TextView) findViewById(R.id.respuesta_inicio);

        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoPassword = (EditText) findViewById(R.id.campoPassword);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister1:
                Intent intentRegister = new Intent(_LoginActivity.this, Register1Activity.class);
                startActivity(intentRegister);
                break;
            case R.id.btnLogin:
                muestraUsuarios();
                break;
        }
    }

    private void muestraUsuarios(){



        String email = campoEmail.getText().toString();
        String password = campoPassword.getText().toString();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/iniciar_sesion.php?email="+email+"&password="+password;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                Log.d("Test", "Test");

                //int count = 0;


                try {
                    Log.d("Test1", "Test2");

                        JSONObject jsonObject = response.getJSONObject(0);
                        Boolean respuesta = jsonObject.getBoolean("respuesta");


                        if (respuesta.equals(true)){
                            Intent intentLogin = new Intent(_LoginActivity.this, Inicio2Activity.class);
                            startActivity(intentLogin);
                        }else{
                            txtRespuesta.setText("El email y la contraseña no coinciden");
                        }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();

            }
        });

        //Toast.makeText(context, "aqui4", Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        //MySingleton.getInstance(context).addToRequestque(jsonArrayRequest);





    }
}
