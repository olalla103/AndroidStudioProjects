package com.example.propuesta11_2;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ImageView(this)); // Establecer la vista personalizada
    }

    // Clase interna que extiende View para dibujar la imagen
    private static class ImageView extends View {
        private Bitmap bitmap;
        private int screenWidth;
        private int screenHeight;

        public ImageView(Context context) {
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

            // Cargar la imagen desde los recursos y redimensionarla
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mi_imagen);
            bitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // Dibujar la imagen escalada en pantalla
            canvas.drawBitmap(bitmap, 0, 0, new Paint());
        }
    }
}
