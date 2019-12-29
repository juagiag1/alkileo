package com.example.alkileov1.ActivityMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.ActivityDetalle.DetalleNotificacionActivity;
import com.example.alkileov1.ConfirmacionActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.LoginActivity;
import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.Objetos.Usuario;
import com.example.alkileov1.R;
import com.example.alkileov1.Register3Activity;
import com.example.alkileov1.localDB.CamposBBDD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatosPersonalesActivity extends AppCompatActivity {
    RecyclerView.Adapter adapter;

    int id, valorContador = 0;

    Button btnActualizar;
    RadioGroup radioTrato;
    RadioButton tratoSr, tratoSra, tratoElegido;
    EditText nombre, apellidos, email, password;
    String trato, rol;

    TextView rolActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        setTitle("Actualiza tus datos");

        Bundle datos = this.getIntent().getExtras();


        id = datos.getInt("id");;
        rol = datos.getString("rol");
        trato = datos.getString("trato");

        rolActual = (TextView) findViewById(R.id.rolActualizar);
        btnActualizar= (Button) findViewById(R.id.btnRegistrarseActualizar);

        radioTrato = (RadioGroup) findViewById(R.id.radioTratoActualizar);
        tratoSr = (RadioButton) findViewById(R.id.radioSrActualizar);
        tratoSra = (RadioButton) findViewById(R.id.radioSraActualizar);

        nombre = (EditText) findViewById(R.id.registroNombreActualizar);
        apellidos = (EditText) findViewById(R.id.registroApellidosActualizar);
        email = (EditText) findViewById(R.id.registroEmailActualizar);
        password = (EditText) findViewById(R.id.registroPasswordActualizar);
        nombre.setText("");
        apellidos.setText("");
        email.setText("");
        password.setText("");

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarTrato();
                actualizarUsuario();
                comprobarTratoInicial();
            }
        });

        password.setText("123456");


        muestraUsuario();

        comprobarTratoInicial();

       // Toast.makeText(this,trato, Toast.LENGTH_SHORT).show();

        if (rol.equals("INQ")){
            rolActual.setText("INQUILINO");
            //btnINQ.setBackgroundDrawable(getDrawable(R.drawable.selectorizquierdorelleno));
        }else if (rol.equals("PRO")){
            rolActual.setText("PROPIETARIO");
            //btnPRO.setBackgroundDrawable(getDrawable(R.drawable.selectorderechorelleno));
        }





    }


    private void actualizarUsuario(){
        String URL = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/actualizar_usuario.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Cambios guardados", Toast.LENGTH_SHORT).show();
                muestraUsuario();

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

                parametros.put("id", String.valueOf(id));
                parametros.put("trato", trato);
                parametros.put("nombre", nombre.getText().toString());
                parametros.put("apellidos", apellidos.getText().toString());
                parametros.put("email", email.getText().toString());
                parametros.put("password", password.getText().toString());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    private void muestraUsuario(){


        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/consulta_usuario.php?id=75";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {



                try {
                    Log.d("Test1", "Test2");
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        nombre.setText(jsonObject.getString("Nombre"));
                        apellidos.setText(jsonObject.getString("Apellidos"));
                        email.setText(jsonObject.getString("Email"));
                        trato = jsonObject.getString("Trato");
                        //rol = jsonObject.getString("Rol");
                        //Toast.makeText(getApplicationContext(), "consigue consulta", Toast.LENGTH_SHORT).show();

                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error de consulta", Toast.LENGTH_SHORT).show();
                }


            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);



    }

    private void comprobarTrato() {
        valorContador=1;
        int radioId = radioTrato.getCheckedRadioButtonId();
        tratoElegido = findViewById(radioId);
        if (tratoElegido.getId() == R.id.radioSrActualizar){
            trato = "o";
        }else if (tratoElegido.getId() == R.id.radioSraActualizar){
            trato = "a";
        }
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

        startActivity(new Intent(DatosPersonalesActivity.this, Inicio2Activity.class));

        return super.onOptionsItemSelected(item);
    }

    public void comprobarTratoInicial(){
        if (trato.equals("o")){
            tratoSr.setChecked(true);
            tratoSra.setChecked(false);
        }else if(trato.equals("a")){
            tratoSra.setChecked(true);
            tratoSr.setChecked(false);
        }
    }


}
