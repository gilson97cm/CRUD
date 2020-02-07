package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LISTADO extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;


    protected void onPostResume(){
        super.onPostResume();
        CargarListado();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = (ListView) findViewById(R.id.listView);

        CargarListado();
        //// filtrar dats

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LISTADO.this,listado.get(position),Toast.LENGTH_SHORT).show();
                int clave= Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre=listado.get(position).split(" ")[1];
                String apellido=listado.get(position).split(" ")[2];
                /// enviamos parametros paa modificar
                Intent intent = new Intent(LISTADO.this,Modificar.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Nombre", nombre);
                intent.putExtra("Apellido", apellido);
                startActivity(intent);


            }
        });

        ////////7 para poner la flechita de atras
        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();

        }
        return super.onOptionsItemSelected(item);
    }



    private void  CargarListado(){
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas(){
        ArrayList<String> datos = new ArrayList<String>();
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "Select Id, Nombre, Apellido from Personas";
        Cursor c = db.rawQuery(sql,null);
        if (c.moveToFirst()) {
            do {
                String linea = c.getInt(0) + " " + c.getString(1) + " " + c.getString(2);
                datos.add(linea);

            }while(c.moveToNext());

        }
        db.close();
        return datos;


        }


}
