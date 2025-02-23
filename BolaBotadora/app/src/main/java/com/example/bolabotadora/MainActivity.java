package com.example.bolabotadora;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    boolean continuar = true;
    float vx = 0.5f, vy = 0.5f; // Velocidades iniciales
    float ax = 0.001f, ay = 0.001f; // Aceleraciones
    int dt = 10;
    int tiempo = 0;
    Thread hilo = null;
    GraficoView dinamica;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica = new GraficoView(this);
        setContentView(dinamica);
        hilo = new Thread(dinamica);
        hilo.start();
    }

    class GraficoView extends View implements Runnable {
        int x, y, xmax, ymax;
        int radio = 30; // Tamaño de la bola
        Paint paintFondo, paintParticula, paintTexto;

        public GraficoView(Context context) {
            super(context);
            paintFondo = new Paint();
            paintParticula = new Paint();
            paintTexto = new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paintTexto.setColor(Color.BLACK);
            paintTexto.setTextSize(30);
        }

        @Override
        public void run() {
            while (continuar) {
                tiempo += dt;
                vx += ax * dt; // Actualizar velocidad en X
                vy += ay * dt; // Actualizar velocidad en Y
                x += (int) (vx * dt); // Mover en X
                y += (int) (vy * dt); // Mover en Y

                // Rebote en los bordes horizontales
                if (x > xmax - radio) {
                    x = xmax - radio;
                    vx = -vx * 0.9f; // Rebote con pérdida de energía
                    ax = -ax;
                }
                if (x < radio) {
                    x = radio;
                    vx = -vx * 0.9f;
                    ax = -ax;
                }

                // Rebote en los bordes verticales
                if (y > ymax - radio) {
                    y = ymax - radio;
                    vy = -vy * 0.9f;
                    ay = -ay;
                }
                if (y < radio) {
                    y = radio;
                    vy = -vy * 0.9f;
                    ay = -ay;
                }

                postInvalidate();
                try {
                    Thread.sleep(dt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            xmax = w;
            ymax = h;
            x = w / 2;
            y = h / 2;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPaint(paintFondo);
            canvas.drawCircle(x, y, radio, paintParticula);
            canvas.drawText("POSICIÓN=(" + x + "," + y + ")", 50, 50, paintTexto);
            canvas.drawText("TIEMPO=" + tiempo, 50, 90, paintTexto);
        }
    }
}
