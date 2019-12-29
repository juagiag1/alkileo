package com.example.alkileov1.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.alkileov1.R;

public class Chat2IActivity extends AppCompatActivity {
    ImageButton btnEscribir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2_i);


        setTitle("María Blasco");

        btnEscribir = (ImageButton) findViewById(R.id.btnEscribir2i);

        btnEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatP2 = new Intent(Chat2IActivity.this, Chat3IActivity.class);
                startActivity(ChatP2);
            }
        });
    }
}
