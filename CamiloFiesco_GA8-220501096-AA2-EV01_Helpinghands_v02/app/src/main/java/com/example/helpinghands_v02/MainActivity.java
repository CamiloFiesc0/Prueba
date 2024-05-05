package com.example.helpinghands_v02;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.checkerframework.common.returnsreceiver.qual.This;

public class MainActivity extends AppCompatActivity {

EditText id, nombre, telefono, direccion, correo, contraseña, edad;

Button insert, update, delete, view;

DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        nombre = findViewById(R.id.nombre);
        telefono = findViewById(R.id.telefono);
        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);
        contraseña = findViewById(R.id.contraseña);
        edad = findViewById(R.id.edad);

        insert = findViewById(R.id.btninsertar);
        update = findViewById(R.id.btnactualizar);
        delete = findViewById(R.id.btneliminar);
        view = findViewById(R.id.btnver);

    DB = new DBHelper(this);

    insert.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            String idTXT = id.getText().toString();
            String nombreTXT = nombre.getText().toString();
            String telefonoTXT = telefono.getText().toString();
            String direccionTXT = direccion.getText().toString();
            String correoTXT = correo.getText().toString();
            String contraseñaTXT = contraseña.getText().toString();
            String edadTXT = edad.getText().toString();

            Boolean checkinsertData = DB.insertuserdata(idTXT, nombreTXT, telefonoTXT, direccionTXT, correoTXT, contraseñaTXT, edadTXT);
            if (checkinsertData==true){
                Toast.makeText(MainActivity.this,
                                "Dato insertado",
                                Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this,
                                "Datos no insertados",
                                Toast.LENGTH_SHORT).show();
            }
         }
      });


    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String idTXT = id.getText().toString();
            String nombreTXT = nombre.getText().toString();
            String telefonoTXT = telefono.getText().toString();
            String direccionTXT = direccion.getText().toString();
            String correoTXT = correo.getText().toString();
            String contraseñaTXT = contraseña.getText().toString();
            String edadTXT = edad.getText().toString();

            Boolean checkupdateData = DB.updateuserdata(idTXT, nombreTXT, telefonoTXT, direccionTXT, correoTXT, contraseñaTXT, edadTXT);
            if (checkupdateData==true){
                Toast.makeText(MainActivity.this,
                        "Dato actualizado",
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(MainActivity.this,
                        "Datos no actualizados",
                        Toast.LENGTH_SHORT).show();


            }
        }
    });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = id.getText().toString();
                Boolean checkdeleteData = DB.deletedata(idTXT);


                if (checkdeleteData==true){
                    Toast.makeText(MainActivity.this,
                            "Dato Borrado",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,
                            "Datos no borrado",
                            Toast.LENGTH_SHORT).show();


                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,
                                    "Entrada no existe",
                                    Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("id : " + res.getString(0 )+"\n");
                    buffer.append("nombre : " + res.getString(1 )+"\n");
                    buffer.append("telefono : " + res.getString(2 )+"\n");
                    buffer.append("direccion : " + res.getString(3 )+"\n");
                    buffer.append("correo : " + res.getString(4 )+"\n");
                    buffer.append("contraseña : " + res.getString(5 )+"\n");
                    buffer.append("edad : " + res.getString(6 )+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


        }

    }
