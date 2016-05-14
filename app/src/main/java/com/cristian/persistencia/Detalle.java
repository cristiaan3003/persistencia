package com.cristian.persistencia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristian.persistencia.adapter.RVAdapter;
import com.cristian.persistencia.pojo.DBHelperMascota;
import com.cristian.persistencia.pojo.model.Mascota;

import java.util.ArrayList;
import java.util.Collections;

public class Detalle extends AppCompatActivity {
    ImageView imagenDerecha;
    ArrayList mascotas;
    DBHelperMascota helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar miActionBar= (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        ((TextView) findViewById(R.id.toolbar_title)).setText("Pentagram");
        imagenDerecha=((ImageView)findViewById(R.id.imagenDerecha));
        imagenDerecha.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        helper = new DBHelperMascota(Detalle.this);
        mascotas = new ArrayList<Mascota>();
        mascotas = helper.getMascotas();
        Collections.sort(mascotas);// ordeno de forma ascendente a cantidad de likes
        mascotas= new ArrayList<>(mascotas.subList(0,5));//me quedo los 5 que tienen mas cantidad de likes

        if (!mascotas.isEmpty()) {

            RecyclerView rv = (RecyclerView) findViewById(R.id.rv_detalle);
            rv.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);
            rv.setClickable(false);
            rv.setEnabled(false);
            rv.setLayoutFrozen(true);
            rv.setItemAnimator(new DefaultItemAnimator());
            RVAdapter adapter = new RVAdapter(mascotas);//paso listado al adaptador
            rv.setAdapter(adapter);
        }

        
    }
}
