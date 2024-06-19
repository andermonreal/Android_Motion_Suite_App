package com.example.back4appmvcsubactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.back4appmvcsubactivity.databinding.ActivityHistoricoBinding;

public class HistoricoActivity extends AppCompatActivity {
    private static final int SHOW_SUBACTIVITY = 4;

    private ActivityHistoricoBinding binding;
    ListView listView;
    TrajeMovimientoAplicacion historicoDeSimulaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHistoricoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listView = (ListView) findViewById(R.id.list);
        historicoDeSimulaciones = (TrajeMovimientoAplicacion)getApplicationContext();
        historicoDeSimulaciones.recargarDatosLocals();

        historicoDeSimulaciones.getServerDatosMaquinaUpdate(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("posicion", posicion);
                Intent intent = new Intent(getApplicationContext(), EstadoActualMaquinaActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, SHOW_SUBACTIVITY);
            }
        });

    }
    public void onClickVolverMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }
}
