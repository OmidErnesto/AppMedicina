package com.example.appmedicina.Servicios;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.appmedicina.Controlador.Farmacias;

public class FarmaciaReceiver extends BroadcastReceiver {
    private Farmacias farmacias;

    public FarmaciaReceiver(Farmacias farmacias) {
        this.farmacias = farmacias;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String datos = intent.getStringExtra("datos");
        if (farmacias != null) {
            farmacias.onDatosReceived(datos);
        }
    }
}
