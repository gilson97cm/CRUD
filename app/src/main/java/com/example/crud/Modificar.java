package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    EditText ptnom,ptape;
    Button btmodificar;
    Button bteliminar;
    int id;
    String nombre;
    String apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b=getIntent().getExtras();

        if (b!=null){

            id= b.getInt("Id");
            nombre=b.getString("Nombre");
            apellido=b.getString("Apellido");
        }


        ptnom = (EditText) findViewById(R.id.ptnom);
        ptape = (EditText) findViewById(R.id.ptape);

        ptnom.setText(nombre);
        ptape.setText(apellido);

        btmodificar =(Button) findViewById(R.id.btmodificar);
        bteliminar =(Button) findViewById(R.id.bteliminar);


       bteliminar.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Eliminar(id);
               onBackPressed();
            }
        });



        btmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar(id,ptnom.getText().toString(),ptape.getText().toString());
                onBackPressed();
            }
        });

    }

    private void Modificar (int Id,String Nombre,String Apellido){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="update Personas set Nombre='" + Nombre + "', Apellido='" +Apellido+"' where Id=" +Id;
        db.execSQL(sql);
        db.close();
    }


    private void Eliminar (int Id){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="delete from Personas  Where Id=" +Id;
        db.execSQL(sql);
       db.close();
   }
}
