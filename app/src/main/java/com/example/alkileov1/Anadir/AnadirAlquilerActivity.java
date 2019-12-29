package com.example.alkileov1.Anadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.ActivityDetalle.DetalleAlquilerActivity;
import com.example.alkileov1.ActivityDetalle.DetalleNotificacionActivity;
import com.example.alkileov1.ActivityMenu.AlquileresActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.Objetos.Usuario;
import com.example.alkileov1.R;
import com.example.alkileov1.RecyclerView.RecyclerAdapter;
import com.example.alkileov1.RecyclerView.RecyclerAdapterUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnadirAlquilerActivity extends AppCompatActivity {
    int id;

    ArrayList<Usuario> arrayListUsuario = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    EditText editTextResult;
    String result;

    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;
    Button abrirPopUp, btnBorrarSi, btnBorrarNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_alquiler);

        setTitle("Añade un nuevo alquiler");

        Bundle datos = getIntent().getExtras();
        if (datos!=null){
            id = datos.getInt("id_prop");
        }


        editTextResult = (EditText) findViewById(R.id.campoBuscar);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewResultadoBusqueda);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        editTextResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                result = editTextResult.getText().toString();
                //result = "carlos";
                arrayListUsuario = muestraUsuarios();
                adapter = new RecyclerAdapterUsuario(getApplicationContext(),arrayListUsuario );
                ((RecyclerAdapterUsuario) adapter).setOnCLickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Toast.makeText(AnadirAlquilerActivity.this, "presionado", Toast.LENGTH_SHORT).show();
                        /*layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                        popupView = layoutInflater.inflate(R.layout.popup_anadir_usuario, null);
                        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.MATCH_PARENT,
                                RadioGroup.LayoutParams.MATCH_PARENT);


                        popupWindow.showAsDropDown(getCurrentFocus(), 0, 0);*/

                        Intent popup = new Intent(AnadirAlquilerActivity.this, AnadirUsuarioActivity.class);
                        int id_usuario = arrayListUsuario.get(recyclerView.getChildAdapterPosition(v)).getId();
                        String nombre =arrayListUsuario.get(recyclerView.getChildAdapterPosition(v)).getNombre();
                        popup.putExtra("id", id);
                        popup.putExtra("id_usuario", id_usuario);
                        popup.putExtra("nombre", nombre);
                        startActivity(popup);
                        //Toast.makeText(AnadirAlquilerActivity.this, String.valueOf(id_usuario), Toast.LENGTH_SHORT).show();

                    }
                });
                recyclerView.setAdapter(adapter);
                //Toast.makeText(AnadirAlquilerActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        });


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
            Intent volver = new Intent(AnadirAlquilerActivity.this, AlquileresActivity.class);
            volver.putExtra("id", id);
            startActivity(volver);
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Usuario> muestraUsuarios(){
        arrayListUsuario.clear();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/busqueda_usuarios.php?result="+result;




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
                        usuario.setNombre(jsonObject.getString("Nombre"));
                        usuario.setApellidos(jsonObject.getString("Apellidos"));
                        usuario.setRol(jsonObject.getString("Rol"));
                        usuario.setId(jsonObject.getInt("Id"));
                        arrayListUsuario.add(usuario);
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
        return arrayListUsuario;


    }
}
