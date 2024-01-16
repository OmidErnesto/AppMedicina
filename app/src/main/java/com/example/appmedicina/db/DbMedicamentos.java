package com.example.appmedicina.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.appmedicina.Controlador.Medicamentos;
import com.example.appmedicina.Entidades.E_Medicamentos;

import java.util.ArrayList;

public class DbMedicamentos extends DbHelper{

    Context context;
    public DbMedicamentos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String nombre, String fecha, String hora, double cantidad, String nota, byte[] imagen){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("fecha", fecha);
            values.put("hora", hora);
            values.put("cantidad", cantidad);
            values.put("nota", nota);
            values.put("imagen", imagen);

            id = db.insert(TABLE_MEDICAMENTOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<E_Medicamentos> mostrarMedicamentos(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<E_Medicamentos> listaMedicamentos = new ArrayList<>();
        E_Medicamentos medicamento = null;
        Cursor cursorMedicamentos = null;

        cursorMedicamentos = db.rawQuery("SELECT * FROM " + TABLE_MEDICAMENTOS,null);

        if(cursorMedicamentos.moveToFirst()){
            do{
                medicamento = new E_Medicamentos();
                medicamento.setId(cursorMedicamentos.getInt(0));
                medicamento.setNombreMedicamento(cursorMedicamentos.getString(1));
                medicamento.setFechaMedicamento(cursorMedicamentos.getString(2));
                medicamento.setHoraMedicamento(cursorMedicamentos.getString(3));
                medicamento.setCantidadMedicamento(cursorMedicamentos.getDouble(4));
                medicamento.setNotaMedicamento(cursorMedicamentos.getString(5));
                medicamento.setImageMedicamento(cursorMedicamentos.getBlob(6));
                listaMedicamentos.add(medicamento);

            } while (cursorMedicamentos.moveToNext());
        }

        cursorMedicamentos.close();

        return listaMedicamentos;

    }

}
