package com.example.alkileov1.Anadir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.alkileov1.ConfirmacionActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.R;
import com.example.alkileov1.Register3Activity;

import java.util.HashMap;
import java.util.Map;


public class AnadirFacturaActivity extends AppCompatActivity {

    EditText concepto, importe;
    Button insertar;

    String rol;
    int id;
    String trato;
    int padre;
    int num_alq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_factura);

        setTitle("Añadir Factura");

        rol = "INQ";
        id = 6;
        trato= "o";
        padre = 5;
        num_alq = 2;

        concepto = (EditText) findViewById(R.id.anadirFacturaConcepto);
        importe = (EditText) findViewById(R.id.anadirFacturaImporte);
        insertar = (Button) findViewById(R.id.anadirFacturaInsertar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("https://carlosgimeno.es/alkileo/ANDROID/api/v1/insertar_factura.php");
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
        menu.findItem(R.id.op4).setVisible(true);
        menu.findItem(R.id.op5).setVisible(true);
        menu.findItem(R.id.op6).setVisible(false);
        menu.findItem(R.id.op7).setVisible(false);
        menu.findItem(R.id.op8).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.op4:
                Toast.makeText(getApplicationContext(), "Opcion 4", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op5:
                Toast.makeText(getApplicationContext(), "Opcion 5", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op9:
                Intent volver = new Intent(AnadirFacturaActivity.this, Inicio2Activity.class);
                volver.putExtra("Ruta",2);
                volver.putExtra("rol","INQ");
                volver.putExtra("id", 6);
                volver.putExtra("trato", "o");
                volver.putExtra("padre", 5);
                volver.putExtra("num_alq", 2);
                startActivity(volver);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                importe.setText("");
                concepto.setText("");
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

                parametros.put("padre", String.valueOf(id));
                parametros.put("num_alq", String.valueOf(num_alq));
                parametros.put("concepto", concepto.getText().toString());
                parametros.put("importe", importe.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
