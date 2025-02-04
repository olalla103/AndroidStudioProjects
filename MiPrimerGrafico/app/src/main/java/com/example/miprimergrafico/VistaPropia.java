package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VistaPropia extends View {

    private int anchoPantalla;
    private int altoPantalla;

    public VistaPropia(Context context) {
        super(context);
    }

    public VistaPropia(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Obtener las dimensiones de la pantalla
        anchoPantalla = getWidth();
        altoPantalla = getHeight();

        // Crear un margen para alejar las líneas y el texto del borde
        int margen = 50;

        // Configurar el pincel para la línea roja
        Paint pincelLinea = new Paint();
        pincelLinea.setColor(Color.RED);
        pincelLinea.setStrokeWidth(10);

        // Dibujar una línea vertical roja (margen desde el borde izquierdo)
        canvas.drawLine(margen, 0, margen, altoPantalla, pincelLinea);

        // Configurar el pincel para la línea verde
        Paint pincelLineaVerde = new Paint();
        pincelLineaVerde.setColor(Color.GREEN);
        pincelLineaVerde.setStrokeWidth(10);

        // Dibujar una línea horizontal verde centrada en la pantalla
        canvas.drawLine(0, altoPantalla / 2f, anchoPantalla, altoPantalla / 2f, pincelLineaVerde);

        // Configurar el pincel para el texto
        Paint pincelTexto = new Paint();
        pincelTexto.setColor(Color.BLACK);
        pincelTexto.setTextSize(50);

        // Dibujar el texto desplazado con un margen desde la izquierda
        canvas.drawText("width: " + anchoPantalla, margen + 20, 60, pincelTexto);
        canvas.drawText("height: " + altoPantalla, margen + 20, 120, pincelTexto);

        // Calcular y mostrar la escala (opcional)
        float escala = getResources().getDisplayMetrics().density;
        canvas.drawText("escala: " + escala, margen + 20, 180, pincelTexto);
    }
}
