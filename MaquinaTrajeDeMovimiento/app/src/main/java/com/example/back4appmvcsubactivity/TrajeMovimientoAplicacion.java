package com.example.back4appmvcsubactivity;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.example.back4appmvcsubactivity.Model.DatosMaquina;
import com.example.back4appmvcsubactivity.Model.Sensores;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class TrajeMovimientoAplicacion extends Application {
    private List<Sensores> listaDeSimulaciones = new ArrayList<>();
    private List<DatosMaquina> listaDeDatosMaquina = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Sensores.class);
        ParseObject.registerSubclass(DatosMaquina.class);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());

        recargarDatosLocals();
    }



    public void recargarDatosLocals() {
        ParseQuery<DatosMaquina> query = ParseQuery.getQuery("DatosMaquina");
        query.findInBackground((objects, e) -> {
            listaDeDatosMaquina = objects;
        });
        ParseQuery<Sensores> query2 = ParseQuery.getQuery("Sensores");
        query2.findInBackground((objects, e) -> {
            listaDeSimulaciones = objects;
        });
    }


/////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-//////////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-///// SENSORES /////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-//////////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-/////

    public void getServerSensoresUpdateList(ListView listView) {

        ParseQuery<Sensores> query = ParseQuery.getQuery("Sensores");
        query.findInBackground((objects, e) -> {
            if (e == null) {
                listaDeSimulaciones = objects;

                ArrayAdapter<Sensores> todoItemsAdapter = new ArrayAdapter<Sensores>(listView.getContext(),R.layout.row_layout, R.id.listText, listaDeSimulaciones);

                listView.setAdapter(todoItemsAdapter);
                todoItemsAdapter.notifyDataSetChanged();

                Log.d("object query server OK:", "getServerSensoresUpdate()");
            } else {
                Log.d("fail query, reason: " + e.getMessage(), "getServerSensoresUpdate()");
            }
        });
    }

    public void addSimulacionSensores(@NonNull Sensores simulacion) {

        simulacion.saveInBackground(e -> {

            if (e == null) {
                listaDeSimulaciones.add(simulacion);
                Log.d("object saved server OK:", "addObjectUpdate()");
            } else {
                Log.d("fail save, reason: "+ e.getMessage(), "addObjectUpdate()");
            }
        });
    }


    public void updateObjectUpdateSensores(@NonNull Sensores simulacion) {

        simulacion.saveInBackground(e -> {
            if (e == null) {
                Log.d("object updated srv OK:", "updateObjectUpdate()");
            } else {
                Log.d("fail update, reason: "+ e.getMessage(), "updateObjectUpdate()");
            }
        });
    }

    public int getUltimaSimulacion() {
        System.out.println("Ultima simulación: " + (listaDeSimulaciones.size()-1));
        return listaDeSimulaciones.size()-1; }
    public int getNuevaUltimaSimulacion() {
        System.out.println("Nueva simulacion: " + listaDeSimulaciones.size());
        return listaDeSimulaciones.size(); }

    public Bitmap getFoto(int posicion) {return listaDeSimulaciones.get(posicion).getBitmap();}

    public String toStringSensor(int posicion, int numSensor) {
        float[] sensor = null;
        switch (numSensor) {
            case 0:
                sensor = listaDeSimulaciones.get(posicion).getCabeza();
                break;
            case 1:
                sensor = listaDeSimulaciones.get(posicion).getManoDer();
                break;
            case 2:
                sensor = listaDeSimulaciones.get(posicion).getManoIzq();
                break;
            case 3:
                sensor = listaDeSimulaciones.get(posicion).getPieDer();
                break;
            case 4:
                sensor = listaDeSimulaciones.get(posicion).getPieIzq();
                break;
        }
        assert sensor != null;
        return "[x: " + sensor[0] + " - y: " + sensor[1] + "]";
    }
    public boolean listaVaciaSensores() { return listaDeSimulaciones.isEmpty(); }
    public int getNuevoIDSensores() {return listaDeSimulaciones.size(); }
    public Sensores getSensor (int posicion) { return listaDeSimulaciones.get(posicion); }

    public void setearLaMaquinaAlSensor(int posicion, int id_maquina) { listaDeSimulaciones.get(posicion).setIDMaquina(id_maquina); }




    /////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-//////////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-///// MAQUINA /////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-//////////-=-=-=-=-=-=-=-=-=-=-=-=-=-=-/////
    public void addSimulacionMaquina(@NonNull DatosMaquina datosMaquina) {

        datosMaquina.saveInBackground(e -> {

            if (e == null) {
                listaDeDatosMaquina.add(datosMaquina);
                Log.d("object saved server OK:", "addObjectUpdate()");
            } else {
                Log.d("fail save, reason: "+ e.getMessage(), "addObjectUpdate()");
            }
        });
    }

    public void getServerDatosMaquinaUpdate(ListView listView) {

        ParseQuery<DatosMaquina> query = ParseQuery.getQuery("DatosMaquina");
        query.findInBackground((objects, e) -> {
            if (e == null) {
                listaDeDatosMaquina = objects;

                ArrayAdapter<DatosMaquina> todoItemsAdapter = new ArrayAdapter<DatosMaquina>(listView.getContext(),R.layout.row_layout, R.id.listText, listaDeDatosMaquina);

                listView.setAdapter(todoItemsAdapter);
                todoItemsAdapter.notifyDataSetChanged();

                Log.d("object query server OK:", "getServerSensoresUpdate()");
            } else {
                Log.d("fail query, reason: " + e.getMessage(), "getServerSensoresUpdate()");
            }
        });
    }

    public void getServerSensoresUpdate(Spinner spinner) {

        ParseQuery<Sensores> query = ParseQuery.getQuery("Sensores");
        query.findInBackground((objects, e) -> {
            if (e == null) {
                listaDeSimulaciones = objects;

                ArrayAdapter<Sensores> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, listaDeSimulaciones);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                Log.d("object query server OK:", "getServerSensoresUpdate()");
            } else {
                Log.d("fail query, reason: " + e.getMessage(), "getServerSensoresUpdate()");
            }
        });
    }
    public String toStringMaquina(int posicion, int numDatoMaquina) {
        switch (numDatoMaquina) {
            case 0:
                return ""+listaDeDatosMaquina.get(posicion).getAltura();
            case 1:
                return ""+listaDeDatosMaquina.get(posicion).getAnchura();
            case 2:
                return ""+listaDeDatosMaquina.get(posicion).getEstado();
            case 3:
                return ""+listaDeDatosMaquina.get(posicion).getBateria();
            case 4:
                int indiceDeSensor = listaDeDatosMaquina.get(posicion).getIDSimulacion();
                return ""+listaDeSimulaciones.get(indiceDeSensor);
        }
        return "";
    }
    public int getNuevaUltimaSimulacionMaquina() {
        return listaDeDatosMaquina.size(); }
    public int getSimulacionSensorMaquina(int posicion) { return listaDeDatosMaquina.get(posicion).getIDSimulacion(); }

    public int getNuevoIDMaquina() {return listaDeDatosMaquina.size(); }
    public boolean listaVaciaMaquina() { return listaDeDatosMaquina.isEmpty(); }
}
