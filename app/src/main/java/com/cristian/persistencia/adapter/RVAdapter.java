package com.cristian.persistencia.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristian.persistencia.R;
import com.cristian.persistencia.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by asusn56 on 02/05/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MascotaViewHolder>{



    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView mascotaName;
        TextView mascotaRanting;
        ImageView mascotaPhoto;
        ImageView iconoHueso;

        MascotaViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            mascotaName = (TextView)itemView.findViewById(R.id.nombre_perro);
            mascotaRanting = (TextView)itemView.findViewById(R.id.rating);
            mascotaPhoto = (ImageView)itemView.findViewById(R.id.imagenPerro);
            iconoHueso =(ImageView)itemView.findViewById(R.id.iconoHueso);
        }

    }



    ArrayList<Mascota> mascotas;

    public RVAdapter(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }



    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_mascota, viewGroup, false);
        MascotaViewHolder pvh = new MascotaViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int i) {
        final Mascota masco=mascotas.get(i);
        mascotaViewHolder.mascotaName.setText(masco.getNombre());
        mascotaViewHolder.mascotaPhoto.setImageResource(masco.getImagen());
        mascotaViewHolder.mascotaRanting.setText(String.valueOf(masco.getRanting()));

       mascotaViewHolder.iconoHueso.setTag(mascotaViewHolder);

       if (masco.getRanting()==0)
        mascotaViewHolder.iconoHueso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MascotaViewHolder mH = (MascotaViewHolder) v.getTag();
                mH.mascotaRanting.setText(String.valueOf(1 + Integer.parseInt(mH.mascotaRanting.getText().toString())));

            }
        });

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}