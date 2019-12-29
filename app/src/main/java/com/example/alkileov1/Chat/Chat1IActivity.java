package com.example.alkileov1.Chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.alkileov1.R;

public class Chat1IActivity extends AppCompatActivity {
    ImageButton btnEscribir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1_i);

        setTitle("María Blasco");

        btnEscribir = (ImageButton) findViewById(R.id.btnEscribiri);

        btnEscribir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatP2 = new Intent(Chat1IActivity.this, Chat2IActivity.class);
                startActivity(ChatP2);
            }
        });
    }
}

