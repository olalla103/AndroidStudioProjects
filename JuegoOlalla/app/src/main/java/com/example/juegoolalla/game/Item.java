package com.example.juegoolalla.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.juegoolalla.R;

public class Item {
    private Bitmap spriteSheet;
    private Bitmap[] frames;
    private int frameIndex;
    private int x, y;
    private int speed = 20;
    private boolean collected;
    private long lastFrameChangeTime;
    private int frameLengthInMilliseconds = 100;
    private int frameWidth, frameHeight;
    private static final int COLUMNS = 4;
    private static final int ROWS = 4;
    private static final int TOTAL_FRAMES = 14;

    public Item(Context context, int startX, int startY) {
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.coin_sprite);

        frameWidth = spriteSheet.getWidth() / 4;
        frameHeight = spriteSheet.getHeight() / 4;

        // Escalar la moneda para hacerla más grande
        int scaleFactor = 2;
        frameWidth *= scaleFactor;
        frameHeight *= scaleFactor;

        frames = new Bitmap[14];

        int frameIndex = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (frameIndex < 14) {
                    frames[frameIndex] = Bitmap.createScaledBitmap(
                            Bitmap.createBitmap(spriteSheet, col * frameWidth / scaleFactor, row * frameHeight / scaleFactor, frameWidth / scaleFactor, frameHeight / scaleFactor),
                            frameWidth, frameHeight, true
                    );
                    frameIndex++;
                }
            }
        }

        x = startX;
        y = startY;  // Ahora usamos la Y que pasamos desde GameView
        collected = false;
        frameIndex = 0;
    }


    public int getX() {
        return x;
    }

    public int getWidth() {
        return frames[frameIndex].getWidth();
    }


    public void update() {
        x -= speed;
        long time = System.currentTimeMillis();
        if (time > lastFrameChangeTime + frameLengthInMilliseconds) {
            frameIndex = (frameIndex + 1) % TOTAL_FRAMES;
            lastFrameChangeTime = time;
        }
    }

    public void draw(Canvas canvas) {
        if (!collected) {
            canvas.drawBitmap(frames[frameIndex], x, y, null);
        }
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public Rect getCollisionRect() {
        return new Rect(x, y, x + frames[frameIndex].getWidth(), y + frames[frameIndex].getHeight());
    }

    public int getY() {
        return y;
    }


}


