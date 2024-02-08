package com.example.appmedicina.Servicios;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.Manifest;
import android.os.Process;
import android.content.pm.PackageManager;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import android.location.Location;
import com.example.appmedicina.BuildConfig;

public class FarmaciaService extends Service {
    public static final String ACTION = "com.AppMedicina.FARMACIA_SERVICE";
    private FusedLocationProviderClient fusedLocationClient;
    private Handler handler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(runnableCode);
        return START_STICKY;
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, Process.myPid(), Process.myUid()) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    new ConsultaAPITask().execute(latitude, longitude);
                                }
                            }
                        });
            } else {
                stopSelf();
            }
            handler.postDelayed(this, 5000); // Repite cada 5 segundos
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnableCode);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ConsultaAPITask extends AsyncTask<Double, Void, String> {
        @Override
        protected String doInBackground(Double... coords) {
            return consultaAPI(coords[0], coords[1]);
        }

        @Override
        protected void onPostExecute(String respuesta) {
            Intent i = new Intent();
            i.setAction(ACTION);
            i.putExtra("datos", respuesta);
            sendBroadcast(i);
        }
    }

    private String consultaAPI(double latitude, double longitude) {
        OkHttpClient client = new OkHttpClient();

        String apiKey = BuildConfig.GOOGLE_MAPS_API_KEY;

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + latitude + "," + longitude +
                "&radius=500&keyword=farmacia&key=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
