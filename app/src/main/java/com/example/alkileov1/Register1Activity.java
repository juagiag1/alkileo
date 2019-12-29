package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register1Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnINQ, btnPRO;
    String rol;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        setTitle("Primer paso");

        intent = new Intent(Register1Activity.this, Register2Activity.class);

        btnINQ = (Button) findViewById(R.id.btnINQ);
        btnPRO = (Button) findViewById(R.id.btnPRO);

        btnINQ.setOnClickListener(this);
        btnPRO.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnINQ:
                rol = "INQ";
                Intent intentINQ = intent;
                intentINQ.putExtra("rol",rol);
                startActivity(intentINQ);
                break;
            case R.id.btnPRO:
                rol = "PRO";
                Intent intentPRO = intent;
                intentPRO.putExtra("rol",rol);
                startActivity(intentPRO);
                break;
        }
    }
}
