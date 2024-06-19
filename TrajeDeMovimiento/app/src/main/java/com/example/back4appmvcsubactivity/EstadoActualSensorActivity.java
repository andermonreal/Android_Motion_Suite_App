package com.example.back4appmvcsubactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstadoActualSensorActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 3;
    TrajeMovimientoAplicacion historicoDeSimulaciones;
    int posicion;
    int posicionMaquina;
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
            textView = ((TextView) findViewById(R.id.textViewDisplay7));
            textView.setText("ID Máquina: " + historicoDeSimulaciones.toStringSensor(posicion, 5));

            imagenCapturada.setImageBitmap(historicoDeSimulaciones.getFoto(posicion));
        }

    }

    public void onClickIrAVerMaquina(View view) {
        posicionMaquina = historicoDeSimulaciones.getSimulacionMaquinaSensor(posicion);

        Bundle bundle = new Bundle();
        bundle.putInt("posicion", posicionMaquina);

        Intent intent = new Intent(getApplicationContext(), EstadoActualMaquinaActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
    public void onClickVolverMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
    public void onClickVolverALaLista(View view) {
        Intent intent = new Intent(getApplicationContext(), HistoricoActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
}
