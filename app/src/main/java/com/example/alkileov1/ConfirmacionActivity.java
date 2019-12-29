package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmacionActivity extends AppCompatActivity implements View.OnClickListener {

    String nombre, trato;
    TextView titulo;
    Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        Bundle datos = this.getIntent().getExtras();
        nombre = datos.getString("nombre");
        titulo = (TextView) findViewById(R.id.titulo);
        trato = datos.getString("trato");

        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        titulo.setText("Bienvenid"+trato+" "+nombre);

        btnIniciarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ConfirmacionActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
