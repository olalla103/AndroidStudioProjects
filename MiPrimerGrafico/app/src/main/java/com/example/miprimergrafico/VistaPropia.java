package com.example.miprimergrafico;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VistaPropia extends View {
    public VistaPropia(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Consigo el ancho y el alto de la pantalla
        int anchoPantalla, altoPantalla;
        anchoPantalla = getWidth();
        altoPantalla = getHeight();

        // Pinto las líneas
        Paint pincel = new Paint();
        pincel.setColor(Color.YELLOW);
        pincel.setStrokeWidth(5);

        canvas.drawRect(0, (float) altoPantalla / 2, anchoPantalla, (float) altoPantalla / 2, pincel);
    }
}
