package com.example.juegoolalla.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.juegoolalla.R;

public class Obstacle {
    private Bitmap bitmap;
    private int x, y;
    private int speed = 20;

    public Obstacle(Context context, int startX, int startY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.obstacle);

        // Ajustar el tamaño del obstáculo
        int newWidth = bitmap.getWidth() * 2;  // Doblar el tamaño
        int newHeight = bitmap.getHeight() * 2;
        bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);

        x = startX;
        y = startY - newHeight + 50;  // Ajustar la altura para que esté bien en el suelo
    }


    public void update() {
        x -= speed; // Mover hacia la izquierda
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public Rect getCollisionRect() {
        int widthReduction = (int) (bitmap.getWidth() * 0.8);  // Reducir 25% del ancho
        int heightReduction = (int) (bitmap.getHeight() * 0.8); // Reducir 33% del alto

        return new Rect(
                x + widthReduction / 2,
                y + heightReduction / 2,
                x + bitmap.getWidth() - widthReduction / 2,
                y + bitmap.getHeight() - heightReduction / 2
        );
    }


}
