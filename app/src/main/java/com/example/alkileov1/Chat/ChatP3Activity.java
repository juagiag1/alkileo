package com.example.alkileov1.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.alkileov1.R;

public class ChatP3Activity extends AppCompatActivity {
    ImageButton btnEscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_p3);

        setTitle("Carlos Pérez");

        btnEscribir = (ImageButton) findViewById(R.id.btnEscribir3);

        btnEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatP2 = new Intent(ChatP3Activity.this, ChatP4Activity.class);
                startActivity(ChatP2);
            }
        });
    }
}
