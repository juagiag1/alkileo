package com.example.alkileov1.ActivityDetalle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.Objetos.Notificacion;
import com.example.alkileov1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetalleNotificacionActivity extends AppCompatActivity {

    ImageView foto;
    TextView titulo, descripcion;

    Button abrirPopUp, btnBorrarSi, btnBorrarNo;

    LayoutInflater layoutInflater;
    View popupView;
    PopupWindow popupWindow;

    int id;
    Boolean exito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_notificacion);

        setTitle("Notificaciones");



        foto = (ImageView) findViewById(R.id.detalleNotificacionFoto);
        titulo = (TextView) findViewById(R.id.detalleNotificacionTitulo);
        descripcion= (TextView) findViewById(R.id.detalleNotificacionDescripcion);

        Bundle datos = getIntent().getExtras();

        String fotoData = datos.getString("fotoData");
        Notificacion notificacion = new Notificacion();
        notificacion.setData(fotoData);
        Bitmap fotoData2 = notificacion.getFoto();

        foto.setImageBitmap(fotoData2);
        titulo.setText(datos.getString("titulo"));
        descripcion.setText(datos.getString("descripcion"));

        id = datos.getInt("id");

        //Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

        abrirPopUp = (Button) findViewById(R.id.btn_borrar_notificacion);

        abrirPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutInflater =(LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                popupView = layoutInflater.inflate(R.layout.popup_borrar, null);
                popupWindow = new PopupWindow(popupView, RadioGroup.LayoutParams.WRAP_CONTENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT);

                btnBorrarNo = popupView.findViewById(R.id.btn_borrar_no);
                btnBorrarSi = popupView.findViewById(R.id.btn_borrar_si);

                btnBorrarSi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        borrarNotificacion2();

                    }
                });

                btnBorrarNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        //Toast.makeText(DetalleNotificacionActivity.this, "No", Toast.LENGTH_SHORT).show();
                    }
                });

                popupWindow.showAsDropDown(abrirPopUp, -150, -360);
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

        startActivity(new Intent(DetalleNotificacionActivity.this, Inicio2Activity.class));

        return super.onOptionsItemSelected(item);
    }

    /*public  void borrarNotificacion(){

        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/borrar_notificacion.php?id=38";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONArray>() {


            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(), "Servicio ejecutado", Toast.LENGTH_SHORT).show();
                exito = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al conectar", Toast.LENGTH_SHORT).show();
                exito = false;

            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonArrayRequest);

    }*/

    public  void borrarNotificacion2(){
        String json_url = "https://carlosgimeno.es/alkileo/ANDROID/api/v1/borrar_notificacion.php?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, json_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                exito = true;
                Intent volver = new Intent(DetalleNotificacionActivity.this, Inicio2Activity.class);
                startActivity(volver);
                Toast.makeText(DetalleNotificacionActivity.this, "Notificación Borrada", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                exito = false;
                popupWindow.dismiss();
                Toast.makeText(DetalleNotificacionActivity.this, "Error al borrar", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
