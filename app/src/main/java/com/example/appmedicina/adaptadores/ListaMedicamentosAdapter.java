package com.example.appmedicina.adaptadores;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmedicina.Entidades.E_Medicamentos;
import com.example.appmedicina.R;

import java.util.ArrayList;

public class ListaMedicamentosAdapter extends RecyclerView.Adapter<ListaMedicamentosAdapter.MedicamentoViewHolder> {

    ArrayList<E_Medicamentos> listaMedicamentos;

    public ListaMedicamentosAdapter(ArrayList<E_Medicamentos> listaMedicamentos){
        this.listaMedicamentos = listaMedicamentos;
    }

    @NonNull
    @Override
    public MedicamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_medicamentos, null, false);
        return new MedicamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicamentoViewHolder holder, int position) {
        holder.viewNombre.setText(listaMedicamentos.get(position).getNombreMedicamento());
        holder.viewFecha.setText("Fecha: " + listaMedicamentos.get(position).getFechaMedicamento());
        holder.viewHora.setText("Hora: " + listaMedicamentos.get(position).getHoraMedicamento());
        holder.viewCantidad.setText("Cantidad tomada: " + String.valueOf(listaMedicamentos.get(position).getCantidadMedicamento()));
        holder.viewNota.setText(listaMedicamentos.get(position).getNotaMedicamento());

        byte[] imagenBytes = listaMedicamentos.get(position).getImageMedicamento();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
        holder.viewImagen.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();
    }

    public class MedicamentoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewFecha, viewHora, viewCantidad, viewNota;
        ImageView viewImagen;
        public MedicamentoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombreMedicamento);
            viewFecha = itemView.findViewById(R.id.viewFechaMedicamento);
            viewHora = itemView.findViewById(R.id.viewHoraMedicamento);
            viewCantidad = itemView.findViewById(R.id.viewCantidadMedicamento);
            viewNota = itemView.findViewById(R.id.viewNotaMedicamento);
            viewImagen = itemView.findViewById(R.id.viewImagenMedicamento);

        }
    }
}
