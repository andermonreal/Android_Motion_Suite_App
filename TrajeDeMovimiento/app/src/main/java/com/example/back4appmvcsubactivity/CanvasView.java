/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.back4appmvcsubactivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {
    public float[][] coordenadas = new float[5][2];
    private int contador = 0;
    private Paint paint;

    public CanvasView(Context context) {
        super(context);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Dibuja un punto en cada coordenada almacenada
        for (int i = 0; i < contador; i++) {
            canvas.drawCircle(coordenadas[i][0], coordenadas[i][1], 25, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (contador < 5) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    float x = event.getX();
                    float y = event.getY();
                    coordenadas[contador][0] = x;
                    coordenadas[contador][1] = y;
                    contador++;
                    invalidate(); // Solicita que se vuelva a dibujar la vista
                    break;
            }
            return true;
        } else {
            return false; // Ya no procesamos más eventos después de 5 clics
        }
    }

    public int getContador() { return this.contador; }

    public void mostrarCoordenadas() {
        for (int i=0; i<coordenadas.length; i++) {
            for (int j=0; j<coordenadas[i].length; j++) {
                System.out.println("M["+i+"]["+j+"]="+coordenadas[i][j]);
            }
        }
    }
}
