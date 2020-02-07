package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   EditText ptnom,ptape;
   Button btguardar, btmostrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ptnom = (EditText) findViewById(R.id.ptnom);
        ptape = (EditText) findViewById(R.id.ptape);

        btguardar =(Button) findViewById(R.id.btguardar);
        btmostrar =(Button) findViewById(R.id.btmostrar);

        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(ptnom.getText().toString(),ptape.getText().toString());
            }
        });

        btmostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LISTADO.class));
            }
        });
    }

    private void guardar(String Nombre, String Apellido) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("Nombre", Nombre);
            c.put("Apellido", Apellido);
            db.insert("PERSONAS",null,c);
            db.close();
            Toast.makeText(this, "Registo Insertado", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {

            Toast.makeText(this, " Error"+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
