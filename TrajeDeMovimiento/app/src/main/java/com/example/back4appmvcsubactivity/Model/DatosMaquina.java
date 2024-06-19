package com.example.back4appmvcsubactivity.Model;


import androidx.annotation.NonNull;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("DatosMaquina")
public class DatosMaquina extends ParseObject{

    public DatosMaquina() {}

    public DatosMaquina(@NonNull int id_maquina, @NonNull float altura, @NonNull float anchura, String estado, @NonNull int bateria, @NonNull int id_sensor) {

        put("id_maquina", id_maquina);
        put("altura", altura);
        put("anchura", anchura);
        put("estado", estado);
        put("bateria", bateria);
        put("id_sensor", id_sensor);
        put("fecha", new Date());
    }

    public int getId_Maquina() { return getInt("id_maquina"); }
    public float getAltura() { return (float) getDouble("altura"); }
    public float getAnchura() { return (float) getDouble("anchura"); }
    public String getEstado() { return getString("estado"); }
    public int getBateria() { return getInt("bateria"); }
    public int getIDSimulacion() { return getInt("id_sensor"); }
    public Date getFecha() { return getDate("fecha");}
    @Override
    public String toString() { return "["+getId_Maquina()+"] Maquina del " + getFecha(); }
}
