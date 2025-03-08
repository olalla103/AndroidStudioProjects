package com.example.juegoolalla.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.juegoolalla.GameOverActivity;
import com.example.juegoolalla.R;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView {
    private GameThread gameThread;
    private Player player;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Item> items;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Explosion> explosions;
    private int score;
    private int lives = 3;
    private Paint scorePaint;
    private SurfaceHolder holder;
    private Random random;
    private Bitmap heartBitmap;
    private int lastObstacleX = 0; // Guarda la posición del último obstáculo
    private static final int GAME_SPEED = 12; // Ajusta según el ritmo deseado
    private SoundPool soundPool;
    private int hitSoundId;
    private MediaPlayer mediaPlayer;


    public GameView(Context context) {
        super(context);
        setFocusable(true);

        holder = getHolder();
        player = new Player(context);
        obstacles = new ArrayList<>();
        items = new ArrayList<>();
        powerUps = new ArrayList<>();
        explosions = new ArrayList<>();
        score = 0;
        random = new Random();

        scorePaint = new Paint();
        scorePaint.setTextSize(50);
        scorePaint.setAntiAlias(true);

        heartBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.corazon);


        // Configurar SoundPool para efectos de sonido
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5) // Máximo 5 sonidos simultáneos
                .setAudioAttributes(audioAttributes)
                .build();

        // Cargar el sonido de choque
        hitSoundId = soundPool.load(context, R.raw.hit_sound, 1);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                generateObstacles();
                generateItems();

                post(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = getWidth();
                        int screenHeight = getHeight();
                        int groundLevel = screenHeight - 200; // Asegurar que coincida con los obstáculos

                        player.setPosition(screenWidth / 5, groundLevel - player.getHeight()); // Lo alinea bien
                    }
                });

                startGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });

        gameThread = new GameThread(this);
    }

    public void startGame() {
        gameThread.setRunning(true);
        gameThread.start();
    }

    public void update() {
        player.update();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            item.update();
            if (item.getX() + item.getWidth() < 0) {
                items.remove(i);
                i--;
            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            explosion.update();
            if (explosion.isFinished()) {
                explosions.remove(i);
                i--;
            }
        }

        // Mover y eliminar obstáculos que salen de la pantalla
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle obstacle = obstacles.get(i);
            obstacle.update();
            if (obstacle.getX() + obstacle.getWidth() < 0) {
                obstacles.remove(i);
                i--;
            }
        }

        // Generar obstáculos con más frecuencia y variabilidad en la distancia
        int minSeparation = 500;  // Reducción de la distancia mínima entre obstáculos
        int maxSeparation = 1500; // Menor distancia máxima para generar más obstáculos

        if (obstacles.isEmpty() || obstacles.get(obstacles.size() - 1).getX() < getWidth() - minSeparation) {
            if (random.nextInt(100) < 15) { // Aumentamos la probabilidad de aparición al 15%
                int startX = getWidth() + minSeparation + random.nextInt(maxSeparation - minSeparation);
                int startY = getHeight() - 200; // Ajustamos la altura de los obstáculos

                obstacles.add(new Obstacle(getContext(), startX, startY));
            }
        }

        // Generar monedas con menor frecuencia que los obstáculos
        if (random.nextInt(100) < 3) { // 4% de probabilidad por frame
            int startX = getWidth() + 300;
            int startY = getHeight() - 400;
            items.add(new Item(getContext(), startX, startY));
        }

        // Verificar colisiones
        checkCollision();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            // 🔹 Dibujar el cielo
            Paint skyPaint = new Paint();
            skyPaint.setColor(0xFF87CEEB); // Azul cielo
            canvas.drawRect(0, 0, getWidth(), getHeight(), skyPaint);

            // 🔹 Dibujar el suelo
            Paint groundPaint = new Paint();
            groundPaint.setColor(0xFF4CAF50); // Verde pasto
            int groundHeight = (int) (getHeight() * 0.20); // 20% de la pantalla
            int groundY = getHeight() - groundHeight;
            canvas.drawRect(0, groundY, getWidth(), getHeight(), groundPaint);

            // 🔹 Dibujar elementos del juego

            // Dibujar obstáculos
            for (Obstacle obstacle : obstacles) {
                obstacle.draw(canvas);
            }

            // Dibujar monedas
            for (Item item : items) {
                item.draw(canvas);
            }

            // Dibujar PowerUps
            for (PowerUp powerUp : powerUps) {
                powerUp.draw(canvas);
            }

            // Dibujar el jugador (Macaron)
            player.draw(canvas);

            // Dibujar explosiones
            for (Explosion explosion : explosions) {
                explosion.draw(canvas);
            }

            // Dibujar los corazones en la parte superior izquierda
            if (heartBitmap != null) {
                int heartSize = 100; // 🔹 Aumenta el tamaño de los corazones
                int heartX = getWidth() - 400; // Ajustar la posición más a la izquierda si es necesario
                int heartY = 30; // Altura de los corazones
                int heartSpacing = 110; // 🔹 Aumenta la separación entre corazones

                for (int i = 0; i < lives; i++) {
                    canvas.drawBitmap(Bitmap.createScaledBitmap(heartBitmap, heartSize, heartSize, false),
                            heartX + (i * heartSpacing), heartY, null);
                }
            } else {
                System.out.println("❌ No se está dibujando heartBitmap.");
            }


            // 🔹 Dibujar la puntuación
            Paint textPaint = new Paint();
            textPaint.setColor(0xFF000000); // Negro
            textPaint.setTextSize(50);
            canvas.drawText("Puntos: " + score, 50, 100, textPaint);
        }
    }

    private void generateItems() {
        if (getWidth() > 0) {
            for (int i = 0; i < 3; i++) {
                int startX = getWidth() + 200; // Fuera de la pantalla
                int startY = getHeight() - 500; // Ajustar altura para que se vean bien
                items.add(new Item(getContext(), startX, startY));

            }
        }
    }

    private void checkCollision() {

        // Reproducir sonido de choque
        soundPool.play(hitSoundId, 1.0f, 1.0f, 1, 0, 1.0f);
        // Verificar colisión con obstáculos
        for (int i = 0; i < obstacles.size(); i++) {
            if (Rect.intersects(player.getCollisionRect(), obstacles.get(i).getCollisionRect())) {
                lives--;
                System.out.println("COLISIÓN con obstáculo. Vidas restantes: " + lives);
                explosions.add(new Explosion(getContext(), player.getX(), player.getY()));

                if (lives <= 0) {
                    gameOver();
                } else {
                    obstacles.remove(i);
                }
                return;
            }
        }

        // Verificar colisión con monedas
        for (int i = 0; i < items.size(); i++) {
            if (Rect.intersects(player.getCollisionRect(), items.get(i).getCollisionRect())) {
                System.out.println("MONEDA RECOGIDA en posición: " + items.get(i).getX() + ", " + items.get(i).getY());
                items.remove(i);
                score += 10; // Sumar puntos
                break;
            }
        }

    }


    private void generateObstacles() {
        if (getWidth() > 0) {
            int startX = getWidth() + 500; // Aparecen fuera de la pantalla

            for (int i = 0; i < 3; i++) { // Generamos 3 obstáculos al inicio
                int startY = getHeight() - 200; // Asegurar que estén en el suelo
                obstacles.add(new Obstacle(getContext(), startX, startY));

                startX += 800; // Espaciado entre obstáculos
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump(); // Hacer que el macaron salte
            System.out.println("Salto detectado");
        }
        return true; // Importante devolver `true` para que el toque se registre
    }

    private void gameOver() {
        System.out.println("GAME OVER: Sin vidas.");
        gameThread.setRunning(false); // Detener el juego

        // Cambiar a la pantalla de Game Over
        Intent intent = new Intent(getContext(), GameOverActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("score", score); // Pasar el puntaje final
        getContext().startActivity(intent);
    }


}
