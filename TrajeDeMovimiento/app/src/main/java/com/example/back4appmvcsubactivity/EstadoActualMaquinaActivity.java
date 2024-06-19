package com.example.back4appmvcsubactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstadoActualMaquinaActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 4;
    TrajeMovimientoAplicacion historicoDeSimulaciones;
    int posicionMaquina;
    int posicionSensores;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_actual_maquina);
        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        posicionMaquina = bundle.getInt("posicion");
        System.out.println("Estoy con: " + posicionMaquina);

        if (historicoDeSimulaciones.listaVaciaMaquina()) {
            System.out.println("[!] Lista vacia");
        } else {
            TextView textView;
            textView = ((TextView) findViewById(R.id.textViewDisplay1));
            textView.setText("Identificador de la simulación: " + posicionMaquina);
            textView = ((TextView) findViewById(R.id.textViewDisplay2));
            textView.setText("Altura: " + historicoDeSimulaciones.toStringMaquina(posicionMaquina, 0));
            textView = ((TextView) findViewById(R.id.textViewDisplay3));
            textView.setText("Anchura: " + historicoDeSimulaciones.toStringMaquina(posicionMaquina, 1));
            textView = ((TextView) findViewById(R.id.textViewDisplay4));
            textView.setText("Estado: " + historicoDeSimulaciones.toStringMaquina(posicionMaquina, 2));
            textView = ((TextView) findViewById(R.id.textViewDisplay5));
            textView.setText("Bateria: " + historicoDeSimulaciones.toStringMaquina(posicionMaquina, 3));
            textView = ((TextView) findViewById(R.id.textViewDisplay6));
            textView.setText("Simulacion: \n" + historicoDeSimulaciones.toStringMaquina(posicionMaquina, 4));
        }

    }
    public void onClickVolver(View view) { finish(); }
    public void onClickVolverMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
}
