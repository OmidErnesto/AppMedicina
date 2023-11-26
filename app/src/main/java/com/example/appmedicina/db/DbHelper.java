package com.example.appmedicina.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION = 2;
    private static  final String DATABASE_NOMBRE = "DbAppMedicina.db";
    public static final String TABLE_MEDICAMENTOS = "t_medicamentos";
    public static final String TABLE_SINTOMAS = "t_sintomas";
    public static final String TABLE_FARMACIAS = "t_farmacias";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_MEDICAMENTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "fecha DATE NOT NULL," +
                "hora TIME NOT NULL," +
                "cantidad REAL NOT NULL," +
                "nota TEXT," +
                "imagen BLOB)");

        db.execSQL("CREATE TABLE " + TABLE_SINTOMAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "fecha DATE NOT NULL," +
                "hora TIME NOT NULL," +
                "intensidad TEXT CHECK (intensidad IN ('Moderado', 'Leve', 'Grave')) NOT NULL," +
                "nota TEXT," +
                "imagen BLOB)");

        db.execSQL("CREATE TABLE " + TABLE_FARMACIAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "telefono TEXT," +
                "horaingreso TIME," +
                "horacierre TIME," +
                "imagen BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_MEDICAMENTOS);
        db.execSQL("DROP TABLE " + TABLE_SINTOMAS);
        db.execSQL("DROP TABLE " + TABLE_FARMACIAS);
        onCreate(db);

    }
}
