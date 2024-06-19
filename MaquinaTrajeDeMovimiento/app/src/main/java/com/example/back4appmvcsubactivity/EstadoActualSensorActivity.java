package com.example.back4appmvcsubactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstadoActualSensorActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 4;
    TrajeMovimientoAplicacion historicoDeSimulaciones;
    int posicion;
    ImageView imagenCapturada;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_actual_sensor);
        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext();


        imagenCapturada = findViewById(R.id.imageView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        posicion = bundle.getInt("posicion");
        System.out.println("Estoy con: " + posicion);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (historicoDeSimulaciones.listaVaciaSensores()) {
            System.out.println("[!] Lista vacia");
        } else {
            TextView textView;
            textView = ((TextView) findViewById(R.id.textViewDisplay1));
            textView.setText("Identificador de la simulación: " + posicion);
            textView = ((TextView) findViewById(R.id.textViewDisplay2));
            textView.setText("Cabeza: " + historicoDeSimulaciones.toStringSensor(posicion, 0));
            textView = ((TextView) findViewById(R.id.textViewDisplay3));
            textView.setText("Mano der: " + historicoDeSimulaciones.toStringSensor(posicion, 1));
            textView = ((TextView) findViewById(R.id.textViewDisplay4));
            textView.setText("Mano izq: " + historicoDeSimulaciones.toStringSensor(posicion, 2));
            textView = ((TextView) findViewById(R.id.textViewDisplay5));
            textView.setText("Pie der: " + historicoDeSimulaciones.toStringSensor(posicion, 3));
            textView = ((TextView) findViewById(R.id.textViewDisplay6));
            textView.setText("Pie izq: " + historicoDeSimulaciones.toStringSensor(posicion, 4));

            imagenCapturada.setImageBitmap(historicoDeSimulaciones.getFoto(posicion));
        }

    }
    public void onClickVolverMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
    public void onClickVolverALaMaquina(View view) {
        finish();
    }
}
