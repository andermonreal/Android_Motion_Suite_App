package com.example.back4appmvcsubactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.back4appmvcsubactivity.Model.Sensores;

public class SimularActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 2;
    TrajeMovimientoAplicacion historicoDeSimulaciones;
    CanvasView canvasView;
    ImageView imagenCapturada;
    Bitmap imagebitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular);

        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext();

        canvasView = findViewById(R.id.canvasView);
        imagenCapturada = findViewById(R.id.imageView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHOW_SUBACTIVITY && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imagebitmap = (Bitmap) extras.get("data");
            imagenCapturada.setImageBitmap(imagebitmap);
        }
    }

    public void onClickGuardar(View view) {
        if (canvasView.getContador() == 5) {
            Sensores simulacion = new Sensores(historicoDeSimulaciones.getNuevoIDSensores(), canvasView.coordenadas[0], canvasView.coordenadas[1], canvasView.coordenadas[2], canvasView.coordenadas[3], canvasView.coordenadas[4], imagebitmap);

            historicoDeSimulaciones.addSimulacionSensores(simulacion);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Bundle bundle = new Bundle();
            bundle.putInt("posicion", historicoDeSimulaciones.getNuevaUltimaSimulacion());

            Intent intent = new Intent(getApplicationContext(), EstadoActualSensorActivity.class);
            intent.putExtras(bundle);

            startActivityForResult(intent, SHOW_SUBACTIVITY);
        } else {
            System.out.println("El contador es: " + canvasView.getContador());
        }

    }
    public void onClickSacarFoto(View view) {
        Intent sacarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (sacarFoto.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(sacarFoto, SHOW_SUBACTIVITY);
        } else {
            Toast.makeText(this, "No se ha encontrado la camara", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickVolverMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
}