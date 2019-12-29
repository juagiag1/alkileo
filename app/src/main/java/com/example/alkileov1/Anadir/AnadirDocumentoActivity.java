package com.example.alkileov1.Anadir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alkileov1.Inicio2Activity;
import com.example.alkileov1.R;

public class AnadirDocumentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_documento);

        setTitle("Anadir Documento");
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
        menu.findItem(R.id.op6).setVisible(true);
        menu.findItem(R.id.op7).setVisible(true);
        menu.findItem(R.id.op8).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){

            case R.id.op6:
                Toast.makeText(getApplicationContext(), "Opcion 6", Toast.LENGTH_SHORT).show();
                break;

            case R.id.op7:
                Toast.makeText(getApplicationContext(), "Opcion 7", Toast.LENGTH_SHORT).show();
                break;


            case R.id.op9:
                Intent volver = new Intent(AnadirDocumentoActivity.this, Inicio2Activity.class);
                volver.putExtra("Ruta",3);
                startActivity(volver);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
