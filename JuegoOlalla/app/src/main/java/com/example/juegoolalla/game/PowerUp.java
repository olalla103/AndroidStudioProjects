package com.example.juegoolalla.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.juegoolalla.R;

public class PowerUp {
    private Bitmap spriteSheet;
    private Bitmap[] frames;
    private int frameIndex;
    private int x, y;
    private int speed = 8;
    private boolean collected;
    private long lastFrameChangeTime;
    private int frameLengthInMilliseconds = 100;
    private int frameWidth, frameHeight;
    private static final int COLUMNS = 3;
    private static final int ROWS = 3;
    private static final int TOTAL_FRAMES = 7; // Solo usaremos los 7 frames

    public PowerUp(Context context, int startX, int startY) {
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.powerup_sprite);

        frameWidth = spriteSheet.getWidth() / COLUMNS;
        frameHeight = spriteSheet.getHeight() / ROWS;
        frames = new Bitmap[TOTAL_FRAMES];

        int frameIndex = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (frameIndex < TOTAL_FRAMES) { // Solo tomamos los primeros 7 frames
                    frames[frameIndex] = Bitmap.createBitmap(spriteSheet, col * frameWidth, row * frameHeight, frameWidth, frameHeight);
                    frameIndex++;
                }
            }
        }

        // Asegurar que el PowerUp aparece dentro de la pantalla
        x = Math.min(startX, context.getResources().getDisplayMetrics().widthPixels - frameWidth);
        y = Math.max(100, startY); // Evita que aparezca fuera de la pantalla

        collected = false;
        frameIndex = 0;
    }


    public void update() {
        x -= speed; // Mueve el PowerUp hacia la izquierda

        long time = System.currentTimeMillis();
        if (time > lastFrameChangeTime + frameLengthInMilliseconds) {
            frameIndex = (frameIndex + 1) % TOTAL_FRAMES; // Cambia de frame en la animación
            lastFrameChangeTime = time;
        }

    }


    public int getY() {
        return y;
    }

    public void draw(Canvas canvas) {
        if (!collected) {
            canvas.drawBitmap(frames[frameIndex], x, y, null);
        }
    }

    public Rect getCollisionRect() {
        return new Rect(x, y, x + frameWidth, y + frameHeight);
    }


    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return frameWidth;
    }
}
