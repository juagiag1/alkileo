package com.example.alkileov1.ActivityDetalle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alkileov1.ActivityMenu.AlquileresActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.Objetos.Alquiler;
import com.example.alkileov1.Objetos.Usuario;
import com.example.alkileov1.R;
import com.example.alkileov1.RecyclerView.RecyclerAdapterAlquiler;
import com.example.alkileov1.RecyclerView.RecyclerAdapterUsuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetalleAlquilerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_alquiler);


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
            startActivity(new Intent(DetalleAlquilerActivity.this, AlquileresActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
