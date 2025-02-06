package com.example.propuesta11_4;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ApellidoView(this)); // Establecer la vista personalizada
    }

    // Clase interna para dibujar el apellido con variaciones
    private static class ApellidoView extends View {
        private Paint paint;

        public ApellidoView(Context context) {
            super(context);
            init();
        }

        private void init() {
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            String apellido = "García";  // Cambia por tu apellido
            int width = getWidth();
            int height = getHeight();

            // Primera variante: Normal, color negro, centrado
            paint.setColor(Color.BLACK);
            paint.setTextSize(100);
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(apellido, width / 2, height / 6, paint);

            // Segunda variante: Más grande, color rojo, alineado a la izquierda
            paint.setColor(Color.RED);
            paint.setTextSize(140);
            paint.setTypeface(Typeface.SERIF);
            paint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(apellido, 50, height / 3, paint);

            // Tercera variante: Inclinación, color azul, escalado
            paint.setColor(Color.BLUE);
            paint.setTextSize(120);
            paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC));
            paint.setTextScaleX(1.5f);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(apellido, width - 50, height / 2, paint);

            // Cuarta variante: Rotación, color verde
            paint.setColor(Color.GREEN);
            paint.setTextSize(110);
            paint.setTextScaleX(1.0f);
            canvas.save();
            canvas.rotate(-30, width / 3, (height * 2) / 3);
            canvas.drawText(apellido, width / 3, (height * 2) / 3, paint);
            canvas.restore();

            // Quinta variante: Pequeño, color morado, desplazado con translate
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(90);
            canvas.save();
            canvas.translate(300, height - 200);
            canvas.drawText(apellido, 0, 0, paint);
            canvas.restore();
        }
    }
}
