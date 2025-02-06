package com.example.propuesta11_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class VistaDibujo extends View {

    public VistaDibujo(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Configuración del pincel
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE); // Solo borde
        paint.setStrokeWidth(8);
        paint.setAntiAlias(true);

        // Dibujar rectángulo (STROKE)
        paint.setColor(0xFFFF0000); // Rojo
        canvas.drawRect(100, 100, 400, 400, paint);

        // Dibujar rectángulo lleno (FILL)
        paint.setStyle(Paint.Style.FILL); // Relleno
        paint.setColor(0xFFFF6600); // Naranja
        canvas.drawRect(450, 100, 750, 400, paint);

        // Dibujar rectángulo con borde y relleno (FILL_AND_STROKE)
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(0xFFFF3300); // Rojo oscuro
        canvas.drawRect(800, 100, 1100, 400, paint);

        // Dibujar círculo (drawCircle)
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(0xFF0000FF); // Azul
        canvas.drawCircle(500, 700, 100, paint);

        // Dibujar óvalo (drawOval)
        RectF oval = new RectF(350, 600, 650, 800);
        paint.setColor(0xFF00FF00); // Verde
        canvas.drawOval(oval, paint);

        // Dibujar rectángulo envolvente (drawRect)
        paint.setColor(0xFFFF0000); // Rojo
        canvas.drawRect(350, 600, 650, 800, paint);
    }
}
