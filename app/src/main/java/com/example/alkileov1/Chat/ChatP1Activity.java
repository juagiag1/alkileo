package com.example.alkileov1.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

import com.example.alkileov1.ActivityMenu.DatosPersonalesActivity;
import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.R;

public class ChatP1Activity extends AppCompatActivity {

    ImageButton btnEscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_p1);

        setTitle("Carlos Pérez");

        btnEscribir = (ImageButton) findViewById(R.id.btnEscribir);

        btnEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatP2 = new Intent(ChatP1Activity.this, ChatP2Activity.class);
                startActivity(ChatP2);
            }
        });
    }


}
