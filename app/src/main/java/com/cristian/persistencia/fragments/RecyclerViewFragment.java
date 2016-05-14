package com.cristian.persistencia.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristian.persistencia.R;
import com.cristian.persistencia.adapter.RVAdapter;
import com.cristian.persistencia.pojo.DBHelperMascota;
import com.cristian.persistencia.pojo.model.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    ArrayList mascotas;
    RecyclerView rv;
    DBHelperMascota helper;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this persistencia
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        helper = new DBHelperMascota(getActivity());
        mascotas = new ArrayList<Mascota>();
        mascotas = helper.getMascotas();
        if (!mascotas.isEmpty()){


        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        RVAdapter adapter = new RVAdapter(mascotas);
        rv.setAdapter(adapter);
        }
        //FIN ADAPTER
        //-------------------------------------------------------------------------------------

        return  v;

    }

}
