package com.example.appmedicina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmedicina.db.DbSintomas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrearSintoma extends AppCompatActivity {

    EditText nombreEditText, fechaEditText, horaEditText, notaEditText;
    Spinner intensidadSpinner;
    ImageView imageViewSintoma;
    Button guardarButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imagenPredeterminadaSintoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sintoma);

        nombreEditText = findViewById(R.id.sintomas_textNombre);
        fechaEditText = findViewById(R.id.sintomas_textFecha);
        horaEditText = findViewById(R.id.sintomas_textHora);
        notaEditText = findViewById(R.id.sintomas_textNota);
        intensidadSpinner = findViewById(R.id.sintomas_spinnerIntensidad);
        String textoSeleccionado = intensidadSpinner.getSelectedItem().toString();

        guardarButton = findViewById(R.id.sintomas_btnGuardar);



        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagenPredeterminadaSintoma == null) {
                    if (textoSeleccionado.equals("Leve")) {
                        imagenPredeterminadaSintoma = Uri.parse("android.resource://com.example.appmedicina/" + R.drawable.leve);
                    } else if (textoSeleccionado.equals("Moderado")) {
                        imagenPredeterminadaSintoma = Uri.parse("android.resource://com.example.appmedicina/" + R.drawable.moderado);
                    } else {
                        imagenPredeterminadaSintoma = Uri.parse("android.resource://com.example.appmedicina/" + R.drawable.grave);
                    }
                }
                DbSintomas dbSintomas = new DbSintomas(CrearSintoma.this);
                try {
                    String nombre = nombreEditText.getText().toString();
                    String fecha = fechaEditText.getText().toString();
                    String hora = horaEditText.getText().toString();
                    String intensidad = textoSeleccionado;
                    String nota = notaEditText.getText().toString();


                    InputStream inputStream = getContentResolver().openInputStream(imagenPredeterminadaSintoma);
                    byte[] bytes = getBytes(inputStream);

                    long id = dbSintomas.insertar(nombre, fecha, hora, intensidad, nota, bytes);

                    if (id != -1) {
                        Toast.makeText(CrearSintoma.this, "Síntoma guardado con éxito", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(CrearSintoma.this, "Error al guardar el síntoma", Toast.LENGTH_SHORT).show();
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }
                finish();
            }
        });

    }

    public void limpiarCampos() {
        nombreEditText.setText("");
        fechaEditText.setText("");
        horaEditText.setText("");
        intensidadSpinner.setSelection(0);
        notaEditText.setText("");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagenPredeterminadaSintoma = data.getData();
            imageViewSintoma.setImageURI(imagenPredeterminadaSintoma);
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}