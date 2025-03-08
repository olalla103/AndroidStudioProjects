package com.example.juegoolalla.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.juegoolalla.R;

public class Player {
    private Bitmap playerImage;
    private int x, y;
    private int groundLevel; // Altura base donde debe estar el macaron
    private boolean isJumping = false;
    private int velocityY = 0; // Velocidad del salto
    private final int GRAVITY = 2; // Gravedad para hacer que caiga de nuevo
    private final int JUMP_STRENGTH = -30; // Fuerza del salto

    public Player(Context context) {
        playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_macaron);

        int newWidth = 150;
        int newHeight = 150;
        playerImage = Bitmap.createScaledBitmap(playerImage, newWidth, newHeight, true);

        // Asegurar que el groundLevel sea el mismo que los obstáculos
        groundLevel = context.getResources().getDisplayMetrics().heightPixels - 200;

        x = 200;
        y = 800; // Posición inicial (se ajustará con groundLevel)
    }

    public void update() {
        if (isJumping) {
            y += velocityY; // Aplicar velocidad vertical
            velocityY += GRAVITY; // Aplicar gravedad

            // Si toca el suelo, termina el salto
            if (y >= groundLevel) {
                y = groundLevel;
                isJumping = false;
                velocityY = 0;
            }
        }
    }

    public void jump() {
        if (!isJumping) { // Solo salta si no está en el aire
            isJumping = true;
            velocityY = JUMP_STRENGTH; // Aplicar la fuerza del salto
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(playerImage, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.groundLevel = newY; // Asegurar que sabe dónde está el suelo
    }

    public int getHeight() {
        return playerImage.getHeight();
    }

    public Rect getCollisionRect() {
        return new Rect(x, y, x + playerImage.getWidth(), y + playerImage.getHeight());
    }

}
