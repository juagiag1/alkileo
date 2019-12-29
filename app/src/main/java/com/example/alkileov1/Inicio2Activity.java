package com.example.alkileov1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.ActivityDetalle.DetalleNotificacionActivity;
import com.example.alkileov1.ActivityDetalle.DetallePeticionActivity;
import com.example.alkileov1.ActivityMenu.AlquileresActivity;
import com.example.alkileov1.ActivityMenu.DatosPersonalesActivity;
import com.example.alkileov1.Anadir.AnadirDocumentoActivity;
import com.example.alkileov1.Anadir.AnadirFacturaActivity;
import com.example.alkileov1.Anadir.AnadirNotificacionActivity;
import com.example.alkileov1.Chat.Chat1IActivity;
import com.example.alkileov1.Chat.ChatP1Activity;
import com.example.alkileov1.Objetos.Documento;
import com.example.alkileov1.Objetos.Factura;
import com.example.alkileov1.Objetos.Mensaje;
import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.Objetos.Peticion;
import com.example.alkileov1.RecyclerView.RecyclerAdapter;
import com.example.alkileov1.RecyclerView.RecyclerAdapterDocumento;
import com.example.alkileov1.RecyclerView.RecyclerAdapterFactura;
import com.example.alkileov1.RecyclerView.RecyclerAdapterMensaje;
import com.example.alkileov1.localDB.CamposBBDD;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Inicio2Activity extends AppCompatActivity {
    private int Ruta=1;
    private TextView mTextMessage;
    private ImageButton anadir;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Notificacion> arrayListNotificacion = new ArrayList<>();
    ArrayList<Factura> arrayListFactura = new ArrayList<>();
    ArrayList<Documento> arrayListDocumento = new ArrayList<>();
    ArrayList<Mensaje> arrayListMensaje = new ArrayList<>();
    ArrayList<Peticion> arrayListPeticion = new ArrayList<>();
    Mensaje mensaje = new Mensaje();

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private int estadoBarra = 0;


    String rol;
    int id;
    String trato;
    int padre;
    int num_alq;

    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    ArrayList<String> variables = new ArrayList<>();


    public boolean onCreateOptionsMenu(Menu menu){
        int ruta = R.menu.overflow_menu;
        getMenuInflater().inflate(ruta, menu);
        return true;
    }


    public boolean onPrepareOptionsMenu(Menu menu) {
        if(Ruta==1) {
            invalidateOptionsMenu();
            if (rol.equals("INQ")){
                menu.findItem(R.id.op1).setVisible(false);
            }else{
                menu.findItem(R.id.op1).setVisible(true);
            }
            menu.findItem(R.id.op2).setVisible(true);
            menu.findItem(R.id.op3).setVisible(true);
            menu.findItem(R.id.op4).setVisible(false);
            menu.findItem(R.id.op5).setVisible(false);
            menu.findItem(R.id.op6).setVisible(false);
            menu.findItem(R.id.op7).setVisible(false);
            menu.findItem(R.id.op9).setVisible(false);
        }else         if(Ruta==2) {
            invalidateOptionsMenu();
            menu.findItem(R.id.op1).setVisible(false);
            menu.findItem(R.id.op2).setVisible(false);
            menu.findItem(R.id.op3).setVisible(false);
            menu.findItem(R.id.op4).setVisible(true);
            menu.findItem(R.id.op5).setVisible(true);
            menu.findItem(R.id.op6).setVisible(false);
            menu.findItem(R.id.op7).setVisible(false);
            menu.findItem(R.id.op9).setVisible(false);
        }else         if(Ruta==3) {
            invalidateOptionsMenu();
            menu.findItem(R.id.op1).setVisible(false);
            menu.findItem(R.id.op2).setVisible(false);
            menu.findItem(R.id.op3).setVisible(false);
            menu.findItem(R.id.op4).setVisible(false);
            menu.findItem(R.id.op5).setVisible(false);
            menu.findItem(R.id.op6).setVisible(true);
            menu.findItem(R.id.op7).setVisible(true);
            menu.findItem(R.id.op9).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(MenuItem item){
        int Id = item.getItemId();

        switch (Id){
            case R.id.op1:
                Intent alquileres = new Intent(Inicio2Activity.this, AlquileresActivity.class);
                alquileres.putExtra("id", id);
                startActivity(alquileres);
                break;

            case R.id.op2:
                Intent datosPersonales = new Intent(Inicio2Activity.this, DatosPersonalesActivity.class);
                datosPersonales.putExtra("id", id);
                datosPersonales.putExtra("rol", rol);
                datosPersonales.putExtra("trato", trato);
                startActivity(datosPersonales);
                break;
            case R.id.op3:
                eliminarUsuario();
                Intent cerrarSsion = new Intent(Inicio2Activity.this, LoginActivity.class);
                startActivity(cerrarSsion);
                break;

            case R.id.op4:
                Toast.makeText(getApplicationContext(), "Opcion 4", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op5:
                Toast.makeText(getApplicationContext(), "Opcion 5", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op6:
                Toast.makeText(getApplicationContext(), "Opcion 6", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op7:
                Toast.makeText(getApplicationContext(), "Opcion 7", Toast.LENGTH_SHORT).show();
                break;


            case R.id.op8:
                Intent chat = new Intent(Inicio2Activity.this, Chat1IActivity.class);
                startActivity(chat);
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    progressBar.setVisibility(View.VISIBLE);
                    setTitle("Notificaciones");
                    Ruta = 1;


                    arrayListNotificacion = muestraNotificaciones();
                    /*if (arrayListNotificacion.size()==0){
                        Toast.makeText(Inicio2Activity.this, "Sin contenido", Toast.LENGTH_SHORT).show();
                    }*/
                    adapter = new RecyclerAdapter(getApplicationContext(),arrayListNotificacion );

                    ((RecyclerAdapter) adapter).setOnCLickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String titulo = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getTitulo();
                            String descripcion = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getDescripcion();
                            String fotoData = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getData();
                            Intent detalleNotificacion = new Intent(Inicio2Activity.this, DetalleNotificacionActivity.class);
                            detalleNotificacion.putExtra("titulo", titulo);
                            detalleNotificacion.putExtra("descripcion", descripcion);
                            detalleNotificacion.putExtra("fotoData", fotoData);
                            startActivity(detalleNotificacion);

                        }
                    });
                    recyclerView.setAdapter(adapter);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if (adapter.getItemCount() == 0){

                                arrayListMensaje.clear();

                                mensaje.setTitulo("Lo sentimos!");
                                mensaje.setDescripcion("Parece que todavía no tienes ninguna notificacion disponible.");

                                arrayListMensaje.add(mensaje);

                                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                                recyclerView.setAdapter(adapter);

                                //Toast.makeText(Inicio2Activity.this, "Sin contenido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);

                    progressBar.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    progressBar.setVisibility(View.VISIBLE);
                    setTitle("Facturas");
                    Ruta = 2;

                    arrayListFactura = muestraFacturas();
                    adapter = new RecyclerAdapterFactura(getApplicationContext(),arrayListFactura );
                    recyclerView.setAdapter(adapter);
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if (adapter.getItemCount() == 0){

                                arrayListMensaje.clear();

                                mensaje.setTitulo("Lo sentimos!");
                                mensaje.setDescripcion("Parece que todavía no tienes ninguna factura disponible.");

                                arrayListMensaje.add(mensaje);

                                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                                recyclerView.setAdapter(adapter);

                                //Toast.makeText(Inicio2Activity.this, "Sin contenido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);
                    progressBar.setVisibility(View.GONE);



                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    progressBar.setVisibility(View.VISIBLE);
                    setTitle("Documentación");
                    Ruta = 3;

                    arrayListDocumento = muestraDocumentacion();
                    adapter = new RecyclerAdapterDocumento(getApplicationContext(),arrayListDocumento );
                    recyclerView.setAdapter(adapter);
                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            if (adapter.getItemCount() == 0){

                                arrayListMensaje.clear();

                                mensaje.setTitulo("Lo sentimos!");
                                mensaje.setDescripcion("Parece que todavía no tienes ningun documento disponible.");

                                arrayListMensaje.add(mensaje);

                                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                                recyclerView.setAdapter(adapter);

                                //Toast.makeText(Inicio2Activity.this, "Sin contenido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, 2000);
                    progressBar.setVisibility(View.GONE);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio2);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //recyclerView.setVisibility(View.INVISIBLE);


        progressBar = (ProgressBar) findViewById(R.id.mi_progresBar);






        Boolean recordar =false;
        Bundle datos = getIntent().getExtras();
        if (datos!=null){
            rol= datos.getString("rol");
            id = datos.getInt("id");
            trato=datos.getString("trato");
            padre= datos.getInt("padre");
            num_alq = datos.getInt("num_alq");
            recordar = datos.getBoolean("recordar");
        }




         if (noPrimerRegistro() && recordar){
             registrarUsuario();
         }

         consultarListaPersonas();




        setTitle("Notificaciones");

        anadir = (ImageButton) findViewById(R.id.anadir);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anadir = new Intent(Inicio2Activity.this, AnadirNotificacionActivity.class);
                String opcion="";
                switch (Ruta){
                    case 1:
                        opcion = "Notificacion";
                        anadir = new Intent(Inicio2Activity.this, AnadirNotificacionActivity.class);
                        break;

                    case 2:
                        opcion = "Factura";
                        anadir = new Intent(Inicio2Activity.this, AnadirFacturaActivity.class);
                        break;

                    case 3:
                        opcion = "Documentacion";
                        anadir = new Intent(Inicio2Activity.this, AnadirDocumentoActivity.class);
                        break;
                }
                String texto = "Añadir "+opcion;
               // Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT).show();
                startActivity(anadir);
            }
        });



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        arrayListNotificacion = muestraNotificaciones();
        adapter = new RecyclerAdapter(getApplicationContext(),arrayListNotificacion );

        ((RecyclerAdapter) adapter).setOnCLickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getTitulo();
                String descripcion = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getDescripcion();
                String fotoData = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getData();
                int id = arrayListNotificacion.get(recyclerView.getChildAdapterPosition(v)).getId();
                Intent detalleNotificacion = new Intent(Inicio2Activity.this, DetalleNotificacionActivity.class);
                detalleNotificacion.putExtra("titulo", titulo);
                detalleNotificacion.putExtra("descripcion", descripcion);
                detalleNotificacion.putExtra("fotoData", fotoData);
                detalleNotificacion.putExtra("id", id);
                startActivity(detalleNotificacion);

            }
        });
        recyclerView.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (adapter.getItemCount() == 0){

                    arrayListPeticion.clear();

                    arrayListPeticion = muestraPeticion();




                        /*Intent intent = new Intent(Inicio2Activity.this, DetallePeticionActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);*/


                    /*adapter = new RecyclerAdapterPeticion(getApplicationContext(),arrayListPeticion );
                    recyclerView.setAdapter(adapter);*/


                }
            }
        }, 2000);



    }

    private ArrayList<Notificacion> muestraNotificaciones(){
        arrayListNotificacion.clear();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/notificaciones.php?padre="+padre+"&id="+id+"&num_alq="+num_alq;




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
                        Notificacion notificacion = new Notificacion();
                        notificacion.setTitulo(jsonObject.getString("Titulo"));
                        notificacion.setDescripcion(jsonObject.getString("Descripcion"));
                        notificacion.setData(jsonObject.getString("Data"));
                        notificacion.setId(jsonObject.getInt("Id"));
                        arrayListNotificacion.add(notificacion);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    arrayListMensaje.clear();

                    mensaje.setTitulo("Lo sentimos!");
                    mensaje.setDescripcion("Parece que todavía no tienes ninguna notificación disponible.");

                    arrayListMensaje.add(mensaje);

                    adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                    recyclerView.setAdapter(adapter);
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arrayListMensaje.clear();

                mensaje.setTitulo("Lo sentimos!");
                mensaje.setDescripcion("Parece que ha habido algún error de conexión");

                arrayListMensaje.add(mensaje);

                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                recyclerView.setAdapter(adapter);

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        progressBar.setVisibility(View.GONE);

        return arrayListNotificacion;


    }

    private ArrayList<Factura> muestraFacturas(){
        arrayListFactura.clear();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/facturas.php?padre="+padre+"&id="+id+"&num_alq="+num_alq;




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
                        Factura factura = new Factura();
                        factura.setId(jsonObject.getInt("Id"));
                        factura.setConcepto(jsonObject.getString("Concepto"));
                        factura.setImporte(Float.parseFloat(jsonObject.getString("Importe")));
                        arrayListFactura.add(factura);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    arrayListMensaje.clear();

                    mensaje.setTitulo("Lo sentimos!");
                    mensaje.setDescripcion("Parece que todavía no tienes ninguna factura disponible.");

                    arrayListMensaje.add(mensaje);

                    adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                    recyclerView.setAdapter(adapter);
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arrayListMensaje.clear();

                mensaje.setTitulo("Lo sentimos!");
                mensaje.setDescripcion("Parece que ha habido algún error de conexión.");

                arrayListMensaje.add(mensaje);

                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                recyclerView.setAdapter(adapter);

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        return arrayListFactura;


    }


    private ArrayList<Documento> muestraDocumentacion(){
        arrayListDocumento.clear();

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/documentacion.php?padre="+padre+"&id="+id+"&num_alq="+num_alq;




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
                        Documento documento = new Documento();
                        documento.setId(jsonObject.getInt("Id"));
                        documento.setTitulo(jsonObject.getString("Titulo"));
                        documento.setArchivo(jsonObject.getString("Archivo"));
                        arrayListDocumento.add(documento);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    arrayListMensaje.clear();

                    mensaje.setTitulo("Lo sentimos!");
                    mensaje.setDescripcion("Parece que todavía no tienes ningun documento disponible.");

                    arrayListMensaje.add(mensaje);

                    adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                    recyclerView.setAdapter(adapter);
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                arrayListMensaje.clear();

                mensaje.setTitulo("Lo sentimos!");
                mensaje.setDescripcion("Parece que ha habido algún error de conexión.");

                arrayListMensaje.add(mensaje);

                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                recyclerView.setAdapter(adapter);

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        return arrayListDocumento;


    }

    private ArrayList<Peticion> muestraPeticion(){
        arrayListNotificacion.clear();

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
                        arrayListPeticion.add(peticion);

                    }

                    Intent intent = new Intent(Inicio2Activity.this, DetallePeticionActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                    arrayListMensaje.clear();

                    mensaje.setTitulo("Lo sentimos!");
                    mensaje.setDescripcion("Parece que todavía no tienes ningun documento disponible.");

                    arrayListMensaje.add(mensaje);

                    adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                    recyclerView.setAdapter(adapter);
                }


                adapter.notifyDataSetChanged();
            }
            // }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();

                arrayListMensaje.clear();

                mensaje.setTitulo("Lo sentimos!");
                mensaje.setDescripcion("Parece que todavía no tienes ningun notificación disponible.");

                arrayListMensaje.add(mensaje);

                adapter = new RecyclerAdapterMensaje(getApplicationContext(),arrayListMensaje );
                recyclerView.setAdapter(adapter);

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);
        progressBar.setVisibility(View.GONE);

        return arrayListPeticion;


    }

    private void registrarUsuario() {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        //CONTENT VALUES --> Accedemos a los valores de los campos(android.ContentValues)

        ContentValues values = new ContentValues();
        values.put(CamposBBDD.CAMPO_ID, String.valueOf(id));
        values.put(CamposBBDD.CAMPO_ROL,rol);
        values.put(CamposBBDD.CAMPO_TRATO, trato);
        values.put(CamposBBDD.CAMPO_PADRE, String.valueOf(padre));
        values.put(CamposBBDD.CAMPO_NUM_ALQ, String.valueOf(num_alq));


        Long idResultante = db.insert(CamposBBDD.TABLA_USUARIO, CamposBBDD.CAMPO_ID,values);
        //Toast.makeText(getApplicationContext(), "Registro: "+ idResultante, Toast.LENGTH_LONG).show();

        db.close();
    }

    private void consultarListaPersonas() {
        //int test = 0;

        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ CamposBBDD.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            //test+=1;
            //Toast.makeText(getApplicationContext(), String.valueOf(cursor.getInt(4)), Toast.LENGTH_LONG).show();
            id = cursor.getInt(0);
            variables.add(String.valueOf(cursor.getInt(0)));
            rol = String.valueOf(cursor.getString(1));
            variables.add(String.valueOf(cursor.getString(1)));
            trato = String.valueOf(cursor.getString(2));
            variables.add(String.valueOf(cursor.getString(2)));
            padre = cursor.getInt(3);
            variables.add(String.valueOf(cursor.getInt(3)));
            num_alq = cursor.getInt(4);
            variables.add(String.valueOf(cursor.getInt(4)));

        }

        //Toast.makeText(getApplicationContext(), "Datos escritos "+ test, Toast.LENGTH_LONG).show();
        db.close();
    }

    private boolean noPrimerRegistro() {
        Boolean result=false;
        int test = 0;
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ CamposBBDD.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            test +=1;
        }
        if (test<1){
            result=true;
            //Toast.makeText(getApplicationContext(), "No estaba registrado", Toast.LENGTH_LONG).show();
        }else{
            result = false;
            //Toast.makeText(getApplicationContext(), "Ya estaba registrado", Toast.LENGTH_LONG).show();
        }
        db.close();

        return result;

    }

    private void eliminarUsuario() {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(this, "bd_usuarios", null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] parametros = {String.valueOf(id)};

        db.delete(CamposBBDD.TABLA_USUARIO, CamposBBDD.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Has Cerrado sesion", Toast.LENGTH_LONG).show();
        db.close();

    }

    private void mostrarProgressBar(){
        layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.progress_bar, null);
        popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);

        progressBar = (ProgressBar) popupView.findViewById(R.id.mi_progresBar);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();


        popupWindow.showAsDropDown(recyclerView, 0,0);
    }

    private void ocultarProgressBar(){
        popupWindow.dismiss();
    }

}
