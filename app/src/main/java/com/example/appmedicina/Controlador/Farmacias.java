package com.example.appmedicina.Controlador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicina.R;
import com.example.appmedicina.Servicios.FarmaciaReceiver;
import com.example.appmedicina.Servicios.FarmaciaService;
import com.example.appmedicina.Entidades.E_Farmacia;
import com.example.appmedicina.adaptadores.FarmaciaAdapter;

import java.util.ArrayList;
import java.util.List;

public class Farmacias extends Fragment {
    private FarmaciaReceiver receiver;
    private RecyclerView recyclerView;
    private FarmaciaAdapter adapter;
    private List<E_Farmacia> farmaciaList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Crear e instalar el receptor de transmisión
        receiver = new FarmaciaReceiver(this);
        IntentFilter filter = new IntentFilter(FarmaciaService.ACTION);
        getActivity().registerReceiver(receiver, filter);

        // Inicializar la lista de farmacias y el adaptador
        farmaciaList = new ArrayList<>();
        adapter = new FarmaciaAdapter(farmaciaList);

        // Iniciar el servicio
        Intent intent = new Intent(getActivity(), FarmaciaService.class);
        getActivity().startService(intent);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_farmacias, container, false);

        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewFarmacias);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Desinstalar el receptor de transmisión cuando ya no sea necesario
        getActivity().unregisterReceiver(receiver);
    }

    // Método para mostrar los datos
    private void muestraDatos(String datos) {
        try {
            JSONObject json = new JSONObject(datos);
            JSONArray results = json.getJSONArray("results");

            // Limpiar la lista de farmacias
            farmaciaList.clear();

            for (int i = 0; i < results.length(); i++) {
                JSONObject farmacia = results.getJSONObject(i);

                String nombre = farmacia.getString("name");
                String direccion = farmacia.getString("vicinity");
                String businessStatus = farmacia.has("business_status") ? farmacia.getString("business_status") : "Desconocido";
                boolean isOpen = farmacia.has("opening_hours") && farmacia.getJSONObject("opening_hours").has("open_now") ? farmacia.getJSONObject("opening_hours").getBoolean("open_now") : false;
                double rating = farmacia.has("rating") ? farmacia.getDouble("rating") : 0;

                // Crear una nueva farmacia y añadirla a la lista
                E_Farmacia nuevaFarmacia = new E_Farmacia(nombre, direccion, businessStatus, isOpen, rating);
                farmaciaList.add(nuevaFarmacia);
            }

            // Notificar al adaptador que los datos han cambiado
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void onDatosReceived(String datos) {
        muestraDatos(datos);
        adapter.notifyDataSetChanged();
    }
}
