package com.example.appmedicina.Controlador;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appmedicina.CrearSintoma;
import com.example.appmedicina.Entidades.E_Sintomas;
import com.example.appmedicina.R;
import com.example.appmedicina.adaptadores.ListaSintomasAdapter;
import com.example.appmedicina.db.DbSintomas;


import java.util.ArrayList;

public class Sintomas extends Fragment {

    RecyclerView listaSintomas;
    ArrayList<E_Sintomas> listaArraySintomas;
    DbSintomas dbSintomas;

    public Sintomas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sintomas, container, false);

        listaSintomas = view.findViewById(R.id.recyclerViewSintomas);
        listaSintomas.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbSintomas = new DbSintomas(getActivity());
        listaArraySintomas = new ArrayList<>();

        ListaSintomasAdapter adapter = new ListaSintomasAdapter(dbSintomas.mostrarSintomas());
        listaSintomas.setAdapter(adapter);

        Button btnAgregarSintoma = view.findViewById(R.id.btnAgregarSintoma);
        btnAgregarSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CrearSintoma.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Actualiza la lista de medicamentos
        ListaSintomasAdapter adapter = new ListaSintomasAdapter(dbSintomas.mostrarSintomas());
        listaSintomas.setAdapter(adapter);
    }
}