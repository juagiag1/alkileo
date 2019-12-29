package com.example.alkileov1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Thread multitarea = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent enlace = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(enlace);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        multitarea.start();
    }
}
