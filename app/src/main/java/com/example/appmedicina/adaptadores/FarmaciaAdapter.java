package com.example.appmedicina.adaptadores;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicina.Entidades.E_Farmacia;
import com.example.appmedicina.R;

import java.util.List;

public class FarmaciaAdapter extends RecyclerView.Adapter<FarmaciaAdapter.FarmaciaViewHolder> {
    private List<E_Farmacia> farmacias;

    public FarmaciaAdapter(List<E_Farmacia> farmacias) {
        this.farmacias = farmacias;
    }

    @Override
    public FarmaciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_farmacia, parent, false);
        return new FarmaciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FarmaciaViewHolder holder, int position) {
        E_Farmacia farmacia = farmacias.get(position);
        holder.nombreTextView.setText(farmacia.getNombre());
        holder.direccionTextView.setText(farmacia.getDireccion());
        holder.businessStatusTextView.setText("Estado: " + farmacia.getBusinessStatus());
        holder.isOpenTextView.setText(farmacia.isOpen() ? "Abierto" : "Cerrado");
        holder.ratingTextView.setText("Calificación: " + String.valueOf(farmacia.getRating()) + "/5.0");

        if (farmacia.isOpen()) {
            holder.isOpenTextView.setText("Abierto");
            holder.isOpenTextView.setTextColor(Color.GREEN);
        } else {
            holder.isOpenTextView.setText("Cerrado");
            holder.isOpenTextView.setTextColor(Color.RED);
        }

    }


    @Override
    public int getItemCount() {
        return farmacias.size();
    }

    public static class FarmaciaViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTextView;
        public TextView direccionTextView;
        public TextView businessStatusTextView;
        public TextView isOpenTextView;
        public TextView ratingTextView;

        public FarmaciaViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.viewNombreFarmacia);
            direccionTextView = itemView.findViewById(R.id.viewDireccionFarmacia);
            businessStatusTextView = itemView.findViewById(R.id.viewBusinessStatus);
            isOpenTextView = itemView.findViewById(R.id.viewIsOpen);
            ratingTextView = itemView.findViewById(R.id.viewRating);
        }
    }

}
