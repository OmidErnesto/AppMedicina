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

import com.example.appmedicina.CrearMedicamento;
import com.example.appmedicina.Entidades.E_Medicamentos;
import com.example.appmedicina.MainActivity;
import com.example.appmedicina.R;
import com.example.appmedicina.adaptadores.ListaMedicamentosAdapter;
import com.example.appmedicina.db.DbMedicamentos;

import java.util.ArrayList;

public class Medicamentos extends Fragment {

    RecyclerView listaMedicamentos;
    ArrayList<E_Medicamentos> listaArrayMedicamentos;
    DbMedicamentos dbMedicamentos;
    public Medicamentos() {
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
        View view = inflater.inflate(R.layout.fragment_medicamentos, container, false);

        listaMedicamentos = view.findViewById(R.id.recyclerViewMedicamentos);
        listaMedicamentos.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbMedicamentos = new DbMedicamentos(getActivity());
        listaArrayMedicamentos = new ArrayList<>();

        ListaMedicamentosAdapter adapter = new ListaMedicamentosAdapter(dbMedicamentos.mostrarMedicamentos());
        listaMedicamentos.setAdapter(adapter);

        Button btnAgregarMedicamento = view.findViewById(R.id.btnAgregarMedicamento);
        btnAgregarMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CrearMedicamento.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Actualiza la lista de medicamentos
        ListaMedicamentosAdapter adapter = new ListaMedicamentosAdapter(dbMedicamentos.mostrarMedicamentos());
        listaMedicamentos.setAdapter(adapter);
    }
}