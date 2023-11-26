package com.example.appmedicina.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

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

}
