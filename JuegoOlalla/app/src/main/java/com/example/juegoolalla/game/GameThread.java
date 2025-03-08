package com.example.juegoolalla.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class GameThread extends Thread {
    private GameView gameView;
    private boolean isRunning;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;

    public GameThread(GameView gameView) {
        this.gameView = gameView;
        this.surfaceHolder = gameView.getHolder();
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        while (isRunning) {
            long startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    gameView.update();
                    gameView.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long timeMillis = (System.nanoTime() - startTime) / 1000000;
            long waitTime = Math.max(16 - timeMillis, 0);
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
