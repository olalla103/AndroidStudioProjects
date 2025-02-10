package com.example.olallalopezprueba2eval2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VistaCanvas extends View {

    private Paint paintLine;
    private Paint paintText;
    private float scaleDensity;
    private int widthPx;
    private int heightPx;

    public VistaCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VistaCanvas(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        // Configuración de las líneas discontinuas
        paintLine = new Paint();
        paintLine.setColor(Color.GREEN);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(4f);
        paintLine.setPathEffect(new DashPathEffect(new float[]{20, 10}, 0));

        // Configuración del texto
        paintText = new Paint();
        paintText.setColor(Color.BLACK);
        paintText.setTextAlign(Paint.Align.LEFT);
        paintText.setAntiAlias(true);

        // Obtener densidad de pantalla y dimensiones
        scaleDensity = context.getResources().getDisplayMetrics().scaledDensity;
        widthPx = context.getResources().getDisplayMetrics().widthPixels;
        heightPx = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Fondo blanco
        canvas.drawColor(Color.WHITE);

        // Configuración del tamaño del texto
        float textSize = 20 * scaleDensity;
        paintText.setTextSize(textSize);

        // Espaciado entre las líneas
        float step = 30 * scaleDensity;

        // Variables para las posiciones clave
        float fila544 = 544 * scaleDensity;
        float fila1088 = 1088 * scaleDensity;
        float fila1632 = 1632 * scaleDensity;

        // Dibujar líneas y texto
        float yPosition = 0;
        while (yPosition < heightPx) {
            // Dibujar línea discontinua
            canvas.drawLine(0, yPosition, widthPx, yPosition, paintLine);

            // Dibujar texto regular (coordenada Y)
            String text = String.format("%.2f", yPosition);
            canvas.drawText(text, 10 * scaleDensity, yPosition - 10, paintText);

            yPosition += step;
        }

        // Relación de aspecto en la parte inferior centrada
        String ratioText = "ratio=" + String.format("%.5f", (heightPx / scaleDensity) / (float) widthPx);
        paintText.setTextAlign(Paint.Align.CENTER); // Centrar texto
        paintText.setTextSize(30 * scaleDensity);
        canvas.drawText(ratioText, widthPx / 2f, heightPx - 50, paintText);

        float margen = 50 * scaleDensity;
        canvas.drawText(String.format("fila: 544 escala= %.5f", scaleDensity), 350, 544, paintText);
        canvas.drawText(String.format("fila: 1088 ancho= %d", widthPx), 350, 1088, paintText);
        canvas.drawText(String.format("fila: 1632 alto= %d", heightPx), 350, 1632, paintText);


        // Dibujar texto clave solo en las filas específicas
        if (yPosition == fila544 || yPosition == fila1088 || yPosition == fila1632) {
            String keyText = "";
            if (yPosition == fila544) {
                keyText = "fila: 544 scale=" + String.format("%.5f", scaleDensity);
            } else if (yPosition == fila1088) {
                keyText = "fila: 1088 width=" + widthPx;
            } else if (yPosition == fila1632) {
                keyText = "fila: 1632 height=" + heightPx;
            }

            // Dibujar texto clave centrado en la línea
            paintText.setTextSize(30 * scaleDensity); // Tamaño más grande
            paintText.setTextAlign(Paint.Align.CENTER); // Centrar texto
            canvas.drawText(keyText, widthPx / 2f, yPosition - 10, paintText);

            // Restaurar configuración de texto
            paintText.setTextAlign(Paint.Align.LEFT); // Restaurar alineación
            paintText.setTextSize(textSize); // Restaurar tamaño del texto
        }

    }
}
