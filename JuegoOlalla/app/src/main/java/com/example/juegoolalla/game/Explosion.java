package com.example.juegoolalla.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.SystemClock;

import com.example.juegoolalla.R;

public class Explosion {
    private Bitmap spriteSheet;
    private Bitmap[] frames;
    private int frameIndex;
    private int x, y;
    private long startTime;
    private boolean finished;
    private int frameWidth, frameHeight;
    private static final int COLUMNS = 5; // 5 columnas en el sprite
    private static final int ROWS = 4; // 4 filas en el sprite
    private static final int TOTAL_FRAMES = 16; // Solo usamos los 16 primeros
    private static final int FRAME_DURATION = 60; // Duración de cada frame (ajustable)

    public Explosion(Context context, int x, int y) {
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);

        // Asegurar que el sprite cargó correctamente
        if (spriteSheet == null) {
            throw new RuntimeException("No se pudo cargar explosion_sprite.png");
        }

        // Calcular el tamaño de cada frame
        frameWidth = spriteSheet.getWidth() / COLUMNS;
        frameHeight = spriteSheet.getHeight() / ROWS;
        frames = new Bitmap[TOTAL_FRAMES];

        int frameIndex = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (frameIndex < TOTAL_FRAMES) { // Solo tomamos los primeros 16 frames
                    frames[frameIndex] = Bitmap.createBitmap(spriteSheet, col * frameWidth, row * frameHeight, frameWidth, frameHeight);
                    frameIndex++;
                }
            }
        }

        this.x = x - frameWidth / 2; // Centrar la explosión en la posición
        this.y = y - frameHeight / 2;
        this.startTime = SystemClock.uptimeMillis();
        this.finished = false;
        this.frameIndex = 0;
    }

    public void update() {
        long elapsed = SystemClock.uptimeMillis() - startTime;
        frameIndex = (int) (elapsed / FRAME_DURATION);

        if (frameIndex >= TOTAL_FRAMES) {
            finished = true; // La animación ha terminado
        }
    }

    public void draw(Canvas canvas) {
        if (!finished) {
            canvas.drawBitmap(frames[frameIndex], x, y, null);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
