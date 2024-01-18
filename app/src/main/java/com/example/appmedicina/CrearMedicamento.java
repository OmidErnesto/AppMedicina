package com.example.appmedicina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appmedicina.db.DbMedicamentos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CrearMedicamento extends AppCompatActivity {

    EditText nombreEditText, fechaEditText, horaEditText, cantidadEditText, notaEditText;
    ImageView imageViewMedicamento;
    Button guardarButton, seleccionarImagenButton;
    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri imagenUri;
    private Uri imagenPredeterminada = Uri.parse("android.resource://com.example.appmedicina/" + R.drawable.iconomedicamentospng);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_medicamento);

        nombreEditText = findViewById(R.id.medicamentos_textNombre);
        fechaEditText = findViewById(R.id.medicamentos_textFecha);
        horaEditText = findViewById(R.id.medicamentos_textHora);
        cantidadEditText = findViewById(R.id.medicamentos_textCantidad);
        notaEditText = findViewById(R.id.medicamentos_textNota);
        imageViewMedicamento = findViewById(R.id.imageViewMedicamento);

        seleccionarImagenButton = findViewById(R.id.medicamentos_btnSeleccionarImagen);
        guardarButton = findViewById(R.id.medicamentos_btnGuardar);

        seleccionarImagenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagenUri == null) {
                    imagenUri = imagenPredeterminada;
                }

                DbMedicamentos dbMedicamentos = new DbMedicamentos(CrearMedicamento.this);

                try {
                    InputStream inputStream = getContentResolver().openInputStream(imagenUri);
                    byte[] bytes = getBytes(inputStream);

                    String nombre = nombreEditText.getText().toString();
                    String fecha = fechaEditText.getText().toString();
                    String hora = horaEditText.getText().toString();
                    double cantidad = Double.parseDouble(cantidadEditText.getText().toString());
                    String nota = notaEditText.getText().toString();

                    long id = dbMedicamentos.insertar(nombre,fecha,hora,cantidad,nota,bytes);

                    if (id != -1) {
                        Toast.makeText(CrearMedicamento.this, "Medicamento guardado con éxito", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(CrearMedicamento.this, "Error al guardar el medicamento", Toast.LENGTH_SHORT).show();
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
        cantidadEditText.setText("");
        notaEditText.setText("");
        imageViewMedicamento.setImageURI(imagenPredeterminada);
        imagenUri = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imagenUri = data.getData();
            imageViewMedicamento.setImageURI(imagenUri);
        }
    }

    // Método para convertir InputStream a byte[]
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