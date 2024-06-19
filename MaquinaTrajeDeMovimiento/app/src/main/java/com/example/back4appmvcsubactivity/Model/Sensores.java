package com.example.back4appmvcsubactivity.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;

@ParseClassName("Sensores")
public class Sensores extends ParseObject{
    public Sensores() {
    }

    public Sensores(@NonNull int id_sensor, @NonNull float[] cabeza, @NonNull float[] manoDer, @NonNull float[] manoIzq, @NonNull float[] pieDer, @NonNull float[] pieIzq, Bitmap foto) {

        put("id_sensor", id_sensor);
        put("cabezaX", cabeza[0]);
        put("cabezaY", cabeza[1]);
        put("manoDerX", manoDer[0]);
        put("manoDerY", manoDer[1]);
        put("manoIzqX", manoIzq[0]);
        put("manoIzqY", manoIzq[1]);
        put("pieDerX", pieDer[0]);
        put("pieDerY", pieDer[1]);
        put("pieIzqX", pieIzq[0]);
        put("pieIzqY", pieIzq[1]);
        put("fecha", new Date());

        if (foto != null) {
            ParseFile parseFile = bitmapToParseFile(foto);
            put("foto", parseFile);
        }

        put("id_maquina", 0);
    }

    private ParseFile bitmapToParseFile(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        ParseFile parseFile = new ParseFile("photo.png", byteArray);
        return parseFile;
    }

    public int getId_Sensor() { return getInt("id_sensor"); }
    public float[] getCabeza() {
        float[] sensorArray = new float[2];
        sensorArray[0] = (float) getDouble("cabezaX");
        sensorArray[1] = (float) getDouble("cabezaY");
        return sensorArray;
    }

    public float[] getManoDer() {
        float[] sensorArray = new float[2];
        sensorArray[0] = (float) getDouble("manoDerX");
        sensorArray[1] = (float) getDouble("manoDerY");
        return sensorArray;
    }

    public float[] getManoIzq() {
        float[] sensorArray = new float[2];
        sensorArray[0] = (float) getDouble("manoIzqX");
        sensorArray[1] = (float) getDouble("manoIzqY");
        return sensorArray;
    }

    public float[] getPieDer() {
        float[] sensorArray = new float[2];
        sensorArray[0] = (float) getDouble("pieDerX");
        sensorArray[1] = (float) getDouble("pieDerY");
        return sensorArray;
    }

    public float[] getPieIzq() {
        float[] sensorArray = new float[2];
        sensorArray[0] = (float) getDouble("pieIzqX");
        sensorArray[1] = (float) getDouble("pieIzqY");
        return sensorArray;
    }

    public Bitmap getBitmap() {
        ParseFile fotoFile = getParseFile("foto");
        if (fotoFile != null) {
            try {
                byte[] data = fotoFile.getData();
                return BitmapFactory.decodeByteArray(data, 0, data.length);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setIDMaquina (int id_maquina) { put("id_maquina", id_maquina); }
    public int getIDMaquina () { return getInt("id_maquina"); }

    public Date getFecha() { return getDate("fecha");}

    @Override
    public String toString() {
        return "["+getId_Sensor()+"] Sensor del " + getFecha();
    }
}