package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register2Activity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, apellidos;
    Button btnSiguiente;
    RadioGroup radioTrato;
    RadioButton tratoElegido;
    String trato,rol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        setTitle("Sólo queda un paso más");

        nombre = (EditText) findViewById(R.id.registroNombre);
        apellidos = (EditText) findViewById(R.id.registroApellidos);

        radioTrato = (RadioGroup) findViewById(R.id.radioTrato);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        Bundle datos = this.getIntent().getExtras();
        rol = datos.getString("rol");

        btnSiguiente.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        comprobarTrato();
        Intent intent = new Intent(Register2Activity.this, Register3Activity.class);
        intent.putExtra("rol", rol);
        intent.putExtra("trato", trato);
        intent.putExtra("nombre", nombre.getText().toString());
        intent.putExtra("apellidos", apellidos.getText().toString());
        startActivity(intent);
    }

    private void comprobarTrato() {
        int radioId = radioTrato.getCheckedRadioButtonId();
        tratoElegido = findViewById(radioId);
        if (tratoElegido.getId() == R.id.radioSr){
            trato = "o";
        }else if (tratoElegido.getId() == R.id.radioSra){
            trato = "a";
        }
    }
}
