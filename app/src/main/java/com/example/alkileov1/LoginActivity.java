package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.Objetos.Factura;
import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.Objetos.Usuario;
import com.example.alkileov1.RecyclerView.RecyclerAdapterFactura;
import com.example.alkileov1.RecyclerView.RecyclerAdapterUsuario;
import com.example.alkileov1.localDB.CamposBBDD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText campoEmail, campoPassword;
    Switch switchRecordar;

    Button btnLogin, btnRegister1;
    private Toolbar toolbar;
    TextView txtRespuesta;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Usuario> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Thread multitarea = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(10);

                    Intent enlace = new Intent(LoginActivity.this,Inicio2Activity.class);
                    startActivity(enlace);

                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        setTitle("Bienvenido");

        btnRegister1 = (Button) findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(this);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtRespuesta = (TextView) findViewById(R.id.respuesta_inicio);

        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoPassword = (EditText) findViewById(R.id.campoPassword);

        switchRecordar = (Switch) findViewById(R.id.switchRecordar);

        if (PrimerRegistro()){
            multitarea.start();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister1:
                Intent intentRegister = new Intent(LoginActivity.this, Register1Activity.class);
                startActivity(intentRegister);
                break;
            case R.id.btnLogin:
                /*recyclerView = new RecyclerView(this);
                layoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                arrayList = muestraUsuarios();
                adapter = new RecyclerAdapterUsuario(getApplicationContext(),arrayList );
                recyclerView.setAdapter(adapter);

                Toast.makeText(LoginActivity.this, "aqui 0", Toast.LENGTH_SHORT).show();
                if (arrayList.get(0) != null){
                    Intent intentInicio = new Intent(LoginActivity.this, Inicio2Activity.class);
                    if (switchRecordar.isChecked()){
                        intentInicio.putExtra("recordar", true);
                    }else{
                        intentInicio.putExtra("recordar", false);
                    }
                    startActivity(intentInicio);
                }*/

                Intent intentInicio = new Intent(LoginActivity.this, Inicio2Activity.class);
                intentInicio.putExtra("rol","PRO");
                intentInicio.putExtra("id", 78);
                intentInicio.putExtra("trato", "a");
                intentInicio.putExtra("padre", 75);
                intentInicio.putExtra("num_alq", 1);
                if (switchRecordar.isChecked()){
                    intentInicio.putExtra("recordar", true);
                }else{
                    intentInicio.putExtra("recordar", false);
                }
                startActivity(intentInicio);
                break;
        }
    }

    private ArrayList<Usuario> muestraUsuarios(){
        arrayList.clear();

        String email = campoEmail.getText().toString();
        String password = campoPassword.getText().toString();

        //String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/iniciar_sesion.php?email="+email+"&password="+password;

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/iniciar_sesion.php?email=juagiag1@gmail.com&password=1234";




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
                        Usuario usuario = new Usuario();
                        usuario.setId(jsonObject.getInt("Id"));
                        usuario.setRol(jsonObject.getString("Rol"));
                        usuario.setTrato(jsonObject.getString("Trato"));
                        usuario.setNombre(jsonObject.getString("Nombre"));
                        usuario.setApellidos(jsonObject.getString("Apellidos"));
                        usuario.setEmail(jsonObject.getString("Email"));
                        arrayList.add(usuario);

                        Toast.makeText(LoginActivity.this, "Aqui 1", Toast.LENGTH_SHORT).show();

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "Aqui 2", Toast.LENGTH_SHORT).show();
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Aqui 3", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

        return arrayList;
    }

    private boolean PrimerRegistro() {
        Boolean result=false;
        int test = 0;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ CamposBBDD.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            test +=1;
        }
        if (test<1){
            result=false;
            Toast.makeText(getApplicationContext(), "No estaba registrado", Toast.LENGTH_LONG).show();
        }else{
            result = true;
            Toast.makeText(getApplicationContext(), "Ya estaba registrado", Toast.LENGTH_LONG).show();
        }
        db.close();

        return result;

    }

}
