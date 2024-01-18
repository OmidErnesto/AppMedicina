package com.example.appmedicina.adaptadores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicina.Entidades.E_Sintomas;
import com.example.appmedicina.R;

import java.util.ArrayList;

public class ListaSintomasAdapter extends RecyclerView.Adapter<ListaSintomasAdapter.SintomaViewHolder> {

    ArrayList<E_Sintomas> listaSintomas;

    public ListaSintomasAdapter(ArrayList<E_Sintomas> listaSintomas){
        this.listaSintomas = listaSintomas;
    }

    @NonNull
    @Override
    public SintomaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_sintomas, null, false);
        return new SintomaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SintomaViewHolder holder, int position) {
        holder.viewNombre.setText(listaSintomas.get(position).getNombreSintoma());
        holder.viewFecha.setText("Fecha: " + listaSintomas.get(position).getFechaSintoma());
        holder.viewHora.setText("Hora: " + listaSintomas.get(position).getHoraSintoma());
        holder.viewIntensidad.setText("Intensidad: " + listaSintomas.get(position).getIntensidadSintoma());
        holder.viewNota.setText(listaSintomas.get(position).getNotaSintoma());

        byte[] imagenBytes = listaSintomas.get(position).getImageSintoma();
        if (imagenBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
            holder.viewImagen.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {
        return listaSintomas.size();
    }

    public class SintomaViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewFecha, viewHora, viewIntensidad, viewNota;
        ImageView viewImagen;
        public SintomaViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombreSintoma);
            viewFecha = itemView.findViewById(R.id.viewFechaSintoma);
            viewHora = itemView.findViewById(R.id.viewHoraSintoma);
            viewIntensidad = itemView.findViewById(R.id.viewIntensidadSintoma);
            viewNota = itemView.findViewById(R.id.viewNotaSintoma);
            viewImagen = itemView.findViewById(R.id.viewImagenSintoma);

        }
    }
}