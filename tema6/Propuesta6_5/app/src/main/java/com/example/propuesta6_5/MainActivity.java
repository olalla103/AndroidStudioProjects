package com.example.propuesta6_5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    // CON XML
    private TextView texto; // Declara el TextView como variable global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincula la Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Vinculo el TextView
        texto = findViewById(R.id.texto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId(); // Obtén el ID del elemento seleccionado

        if (id == R.id.MnOp1) {
            texto.setText("Opción 1");
        } else if (id == R.id.MnOp2) {
            texto.setText("Opción 2");
        } else if (id == R.id.MnOp3) {
            texto.setText("Opción 3");
        } else {
            return super.onOptionsItemSelected(item); // Llamar al método padre para IDs desconocidos
        }

        return true; // Indica que el evento fue manejado
    }

    // CON JAVA
//    private static final int MnOp1 = 1;
//    private static final int MnOp1_1 = 2;
//    private static final int MnOp1_2 = 3;
//    private static final int MnOp2 = 4;
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        SubMenu smnu = menu.addSubMenu(Menu.NONE, MnOp1, Menu.NONE, "Opción A desde Java");
//        smnu.add(Menu.NONE, MnOp1, Menu.NONE, "Opción A desde Java");
//        smnu.add(Menu.NONE, MnOp1_1, Menu.NONE, "Opción A.1 desde Java");
//        smnu.add(Menu.NONE, MnOp1_2, Menu.NONE, "Opción A.2 desde Java");
//        menu.add(Menu.NONE, MnOp2, Menu.NONE, "Opción B desde Java");
//        return true;
//    }

}
