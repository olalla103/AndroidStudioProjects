package com.example.propuesta11_3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FigurasView(this)); // Establecer la vista personalizada
    }

    // Clase interna para dibujar figuras geométricas
    private static class FigurasView extends View {
        private Paint paint;

        public FigurasView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setStrokeWidth(5);
            paint.setTextSize(40);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Coordenadas generales
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int rectSize = 200;

            // Cuadrado
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(centerX - rectSize, centerY - rectSize, centerX + rectSize, centerY + rectSize, paint);
            canvas.drawText("drawRect", centerX - 90, centerY - rectSize - 20, paint);

            // Círculo dentro del cuadrado
            paint.setColor(Color.BLUE);
            canvas.drawCircle(centerX, centerY, rectSize / 1.5f, paint);
            canvas.drawText("drawCircle", centerX - 90, centerY + rectSize + 50, paint);

            // Óvalo dentro del cuadrado
            paint.setColor(Color.GREEN);
            RectF oval = new RectF(centerX - rectSize, centerY - rectSize / 2, centerX + rectSize, centerY + rectSize / 2);
            canvas.drawOval(oval, paint);
            canvas.drawText("drawOval", centerX - 90, centerY + rectSize + 100, paint);
        }
    }
}
