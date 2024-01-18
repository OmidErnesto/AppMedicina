package com.example.appmedicina.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.appmedicina.Entidades.E_Medicamentos;
import com.example.appmedicina.Entidades.E_Sintomas;

import java.util.ArrayList;

public class DbSintomas extends DbHelper{
    Context context;
    public DbSintomas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertar(String nombre, String fecha, String hora, String intencidad, String nota, byte[] imagen){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("fecha", fecha);
            values.put("hora", hora);
            values.put("intensidad", intencidad);
            values.put("nota", nota);
            values.put("imagen", imagen);

            id = db.insert(TABLE_SINTOMAS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public ArrayList<E_Sintomas> mostrarSintomas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<E_Sintomas> listaSintomas = new ArrayList<>();
        E_Sintomas sintoma = null;
        Cursor cursorSintomas = null;

        cursorSintomas = db.rawQuery("SELECT * FROM " + TABLE_SINTOMAS,null);

        if(cursorSintomas.moveToFirst()){
            do{
                sintoma = new E_Sintomas();
                sintoma.setId(cursorSintomas.getInt(0));
                sintoma.setNombreSintoma(cursorSintomas.getString(1));
                sintoma.setFechaSintoma(cursorSintomas.getString(2));
                sintoma.setHoraSintoma(cursorSintomas.getString(3));
                sintoma.setIntensidadSintoma(cursorSintomas.getString(4));
                sintoma.setNotaSintoma(cursorSintomas.getString(5));
                sintoma.setImageSintoma(cursorSintomas.getBlob(6));
                listaSintomas.add(sintoma);

            } while (cursorSintomas.moveToNext());
        }

        cursorSintomas.close();

        return listaSintomas;

    }

}
