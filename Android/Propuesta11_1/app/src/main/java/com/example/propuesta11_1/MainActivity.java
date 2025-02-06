package com.example.propuesta11_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ResolutionView(this)); // Establecer la vista personalizada
    }

    // Clase interna que extiende View para dibujar la resolución
    private static class ResolutionView extends View {
        private Paint paint;
        private int screenWidth;
        private int screenHeight;

        public ResolutionView(Context context) {
            super(context);
            init(context);
        }

        private void init(Context context) {
            // Obtener resolución de pantalla
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            screenWidth = metrics.widthPixels;
            screenHeight = metrics.heightPixels;

            // Configurar el Paint para dibujar el texto
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.CENTER);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Dibujar la resolución en el centro de la pantalla
            canvas.drawText("Resolución: " + screenWidth + "x" + screenHeight,
                    getWidth() / 2, getHeight() / 2, paint);
        }
    }
}
