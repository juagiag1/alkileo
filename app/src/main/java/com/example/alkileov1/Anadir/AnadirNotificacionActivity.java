package com.example.alkileov1.Anadir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.R;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AnadirNotificacionActivity extends AppCompatActivity {

    String rol, foto;
    int id;
    String trato;
    int padre;
    int num_alq;


    static final int capturar = 1;//variable constante

    private Bitmap bitmap;

    ImageView imagen;
    Button elegirImagen, insertar;
    EditText titulo, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_notificacion);

        rol = "INQ";
        id = 78;
        trato= "a";
        padre = 75;
        num_alq = 1;

        setTitle("Anadir Notificación");

        imagen = (ImageView) findViewById(R.id.anadirNotificacionImagen);

        elegirImagen = (Button) findViewById(R.id.anadirNotificacionElegirImagen);
        insertar = (Button) findViewById(R.id.anadirNotificacionInsertar);

        titulo= (EditText) findViewById(R.id.anadirNotificacionTitulo);
        descripcion = (EditText) findViewById(R.id.anadirNotificacionDescripcion);


        elegirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceder_a_camara();
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("https://carlosgimeno.es/alkileo/ANDROID/api/v1/insertar_notificacion.php");
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
            menu.findItem(R.id.op1).setVisible(true);
            menu.findItem(R.id.op2).setVisible(true);
            menu.findItem(R.id.op3).setVisible(true);
            menu.findItem(R.id.op4).setVisible(false);
            menu.findItem(R.id.op5).setVisible(false);
            menu.findItem(R.id.op6).setVisible(false);
            menu.findItem(R.id.op7).setVisible(false);
            menu.findItem(R.id.op8).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.op1:
                Toast.makeText(getApplicationContext(), "Opcion 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op2:
                Toast.makeText(getApplicationContext(), "Opcion 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.op3:
                Toast.makeText(getApplicationContext(), "Opcion 3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op6:
                Toast.makeText(getApplicationContext(), "Opcion 6", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op7:
                Toast.makeText(getApplicationContext(), "Opcion 7", Toast.LENGTH_SHORT).show();
                break;


            case R.id.op9:
                Intent volver = new Intent(AnadirNotificacionActivity.this, Inicio2Activity.class);
                volver.putExtra("Ruta",1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == capturar && resultCode == RESULT_OK);
        Bundle almacenarimagen = data.getExtras();
        Bitmap fotorealizada = (Bitmap) almacenarimagen.get("data");
        bitmap = fotorealizada;
        imagen.setImageBitmap(fotorealizada);


    }

    private void acceder_a_camara(){
        Intent hacerfoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Resolve activity -->!= null
        if (hacerfoto.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(hacerfoto, capturar);
        }
    }


    private void ejecutarServicio(String URL){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
                titulo.setText("");
                descripcion.setText("");
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

                parametros.put("padre", String.valueOf(id));
                parametros.put("num_alq", String.valueOf(num_alq));
                parametros.put("titulo", titulo.getText().toString());
                parametros.put("descripcion", descripcion.getText().toString());
                //parametros.put("foto", foto);

                return parametros;
            }
        };

        //Toast.makeText(this, foto, Toast.LENGTH_SHORT).show();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /*public String convertirFoto( Bitmap imagen){
        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.coche);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return  imageString;
    }*/


}
