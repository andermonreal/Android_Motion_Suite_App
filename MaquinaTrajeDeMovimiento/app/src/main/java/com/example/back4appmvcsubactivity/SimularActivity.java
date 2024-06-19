package com.example.back4appmvcsubactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.back4appmvcsubactivity.Model.DatosMaquina;

public class SimularActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 2;
    TrajeMovimientoAplicacion historicoDeSimulaciones;
    private EditText editAltura;
    private EditText editAnchura;
    private EditText editEstado;
    private SeekBar editBateria;
    private Spinner editSimulacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular);

        editAltura = findViewById(R.id.edit_altura);
        editAnchura = findViewById(R.id.edit_anchura);
        editEstado = findViewById(R.id.edit_estado);
        editBateria = findViewById(R.id.edit_bateria);
        editSimulacion = findViewById(R.id.edit_simulacion);

        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext();

        historicoDeSimulaciones.getServerSensoresUpdate(editSimulacion);
    }

    public void onClickGuardar(View view) {

        float altura = Float.parseFloat(editAltura.getText().toString());
        float anchura = Float.parseFloat(editAnchura.getText().toString());
        String estado = editEstado.getText().toString();
        int bateria = editBateria.getProgress();
        int posicionSeleccionada = editSimulacion.getSelectedItemPosition();

        DatosMaquina datosMaquina = new DatosMaquina(historicoDeSimulaciones.getNuevaUltimaSimulacionMaquina(), altura, anchura, estado, bateria, posicionSeleccionada);

        historicoDeSimulaciones.addSimulacionMaquina(datosMaquina);

        historicoDeSimulaciones.setearLaMaquinaAlSensor(posicionSeleccionada, historicoDeSimulaciones.getNuevaUltimaSimulacionMaquina());

        historicoDeSimulaciones.updateObjectUpdateSensores(historicoDeSimulaciones.getSensor(posicionSeleccionada));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Bundle bundle = new Bundle();
        bundle.putInt("posicion", historicoDeSimulaciones.getNuevaUltimaSimulacionMaquina());

        Intent intent = new Intent(getApplicationContext(), EstadoActualMaquinaActivity.class);
        intent.putExtras(bundle);

        startActivityForResult(intent, SHOW_SUBACTIVITY);

    }

    public void onClickVolverMenu(View view) {
        finish();
    }
}