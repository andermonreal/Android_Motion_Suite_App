package com.example.back4appmvcsubactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;


import com.example.back4appmvcsubactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int SHOW_SUBACTIVITY_SIMULAR = 1;
    private static final int SHOW_SUBACTIVITY_HISTORICO = 2;
    private static final int SHOW_SUBACTIVITY_ESTADO = 3;
    TrajeMovimientoAplicacion historicoDeSimulaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext(); // Coger los datos de la aplicacion

        ImageView imageView = findViewById(R.id.fondopantalla);
        imageView.setImageResource(R.drawable.imgmain);
    }

    public void onClickSimularDatos(View view) {
        Intent intent = new Intent(getApplicationContext(), SimularActivity.class);
        startActivityForResult(intent, SHOW_SUBACTIVITY_SIMULAR);
    }

    public void onClickVerHistorico(View view) {
        Intent intent = new Intent(getApplicationContext(), HistoricoActivity.class);
        startActivityForResult(intent, SHOW_SUBACTIVITY_HISTORICO);
    }

    public void onClickUltimaSimulacion(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("posicion", historicoDeSimulaciones.getUltimaSimulacion());
        Intent intent = new Intent(getApplicationContext(), EstadoActualSensorActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, SHOW_SUBACTIVITY_ESTADO);
    }
}