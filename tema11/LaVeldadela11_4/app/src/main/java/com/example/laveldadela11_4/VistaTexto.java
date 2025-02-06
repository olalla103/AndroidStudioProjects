package com.example.laveldadela11_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class VistaTexto extends View {

    public VistaTexto(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Configuración común para el pincel
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Primera línea: Apellidos con tamaño grande y color rojo
        paint.setColor(Color.RED);
        paint.setTextSize(80);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("García López", 50, 150, paint);

        // Segunda línea: Apellidos en azul, inclinados y tamaño mediano
        paint.setColor(Color.BLUE);
        paint.setTextSize(60);
        paint.setTextSkewX(-0.5f); // Inclinación
        paint.setTypeface(Typeface.SANS_SERIF);
        canvas.drawText("García López", 50, 250, paint);

        // Tercera línea: Apellidos en verde, tamaño pequeño y centrados
        paint.setColor(Color.GREEN);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER); // Centrar el texto
        canvas.drawText("García López", canvas.getWidth() / 2, 350, paint);

        // Cuarta línea: Apellidos en naranja, tamaño grande, escala en X
        paint.setColor(Color.rgb(255, 165, 0)); // Naranja
        paint.setTextSize(70);
        paint.setTextScaleX(2.0f); // Escalar horizontalmente
        paint.setTextAlign(Paint.Align.LEFT); // Alinear a la izquierda
        canvas.drawText("García López", 50, 450, paint);

        // Quinta línea: Apellidos en morado, inclinados y justificados a la derecha
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.RIGHT); // Alinear a la derecha
        paint.setTextSkewX(0.5f); // Inclinación positiva
        canvas.drawText("García López", canvas.getWidth() - 50, 550, paint);
    }
}
