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
import com.cristian.persistencia.pojo.Mascota;

import java.util.ArrayList;

public class Detalle extends AppCompatActivity {
    ImageView imagenDerecha;
    ArrayList mascotas;
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


        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota("Mascota 3", 10, R.drawable.perro3));
        mascotas.add(new Mascota("Mascota 1", 8, R.drawable.perro1));
        mascotas.add(new Mascota("Mascota 7", 5, R.drawable.perro7));
        mascotas.add(new Mascota("Mascota 4", 4, R.drawable.perro4));
        mascotas.add(new Mascota("Mascota 5", 1, R.drawable.perro5));


        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_detalle);
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
