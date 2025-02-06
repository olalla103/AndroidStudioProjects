package com.example.propuesta11_3;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RectangulosView(this)); // Establecer la vista personalizada
    }

    // Clase interna para dibujar rectángulos con diferentes estilos
    private static class RectangulosView extends View {
        private Paint paint;

        public RectangulosView(Context context) {
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

            int rectY = getHeight() / 3;
            int rectSpacing = 250;

            // Rectángulo solo con borde
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(100, rectY, 300, rectY + 200, paint);
            canvas.drawText("STROKE", 130, rectY + 230, paint);

            // Rectángulo relleno
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(100 + rectSpacing, rectY, 300 + rectSpacing, rectY + 200, paint);
            canvas.drawText("FILL", 130 + rectSpacing, rectY + 230, paint);

            // Rectángulo con borde y relleno
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(100 + rectSpacing * 2, rectY, 300 + rectSpacing * 2, rectY + 200, paint);
            canvas.drawText("FILL+STROKE", 80 + rectSpacing * 2, rectY + 230, paint);
        }
    }
}

