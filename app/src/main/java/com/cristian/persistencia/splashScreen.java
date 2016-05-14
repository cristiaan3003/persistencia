package com.cristian.persistencia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cristian.persistencia.pojo.DBHelperMascota;
import com.cristian.persistencia.pojo.model.Mascota;

import java.util.ArrayList;
import java.util.Arrays;

public class splashScreen extends AppCompatActivity {

    private boolean estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        cargarPreferencias();
        if(estado){
            Intent intent=new Intent(splashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
           ProsesoCarga prosesoCarga=new ProsesoCarga();
            prosesoCarga.execute();
        }
    }


    //sirven para saber si se insertaron datos
    private  void cargarPreferencias(){
        SharedPreferences misReferencias= getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        estado=misReferencias.getBoolean("isLoad",false); // si hemos cargado registros o no. Por defecto false
    }

    public void guardarPreferencias(boolean valor){
        SharedPreferences misReferencias= getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= misReferencias.edit();
        editor.putBoolean("isLoad",valor);
        editor.commit();
    }

    private  class ProsesoCarga extends AsyncTask<Void,Void,Void>{  //sing wrap


        ProgressDialog dialog;
        ArrayList<Mascota> mascotas= new ArrayList<Mascota>(Arrays.asList(
                new Mascota("Mascota 1",10,R.drawable.perro1),
                new Mascota("Mascota 2",0,R.drawable.perro2),
                new Mascota("Mascota 3",0,R.drawable.perro3),
                new Mascota("Mascota 4",0,R.drawable.perro4),
                new Mascota("Mascota 5",0,R.drawable.perro5),
                new Mascota("Mascota 6",0,R.drawable.perro6),
                new Mascota("Mascota 7",0,R.drawable.perro7)
        )); //inicializo mi listado de mascotas a cargar al inicio de la aplicacion
        @Override
        protected Void doInBackground(Void... params) {
            DBHelperMascota helper= new DBHelperMascota(splashScreen.this);
            for(int i=0; i<mascotas.size();i++)
                helper.insertarMascota(mascotas.get(i)); //inserto listado de mascotas en la bd

            return null;
        }

        @Override
        protected void onPreExecute() {

            dialog =new ProgressDialog(splashScreen.this);
            dialog.setTitle("Esto es el titulo");
            dialog.setMessage("Insertando en BD");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            guardarPreferencias(true);
            if (dialog.isShowing()){
                dialog.dismiss();
            Intent intent=new Intent(splashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
                }
        }
    }
}
