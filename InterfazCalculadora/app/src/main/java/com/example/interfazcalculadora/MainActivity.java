package com.example.interfazcalculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText visor;
    double valor1, valor2;
    boolean suma, resta, multiplicacion, division, potencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.editTextNumberDecimal2); // DONDE SE VAN A VER LOS RESULTADOS

        // BOTONES DE LA CALCULADORA
        Button uno = findViewById(R.id.uno);
        Button dos = findViewById(R.id.dos);
        Button tres = findViewById(R.id.tres);
        Button cuatro = findViewById(R.id.cuatro);
        Button cinco = findViewById(R.id.cinco);
        Button seis = findViewById(R.id.seis);
        Button siete = findViewById(R.id.siete);
        Button ocho = findViewById(R.id.ocho);
        Button nueve = findViewById(R.id.nueve);
        Button cero = findViewById(R.id.cero);
        Button ceroCero = findViewById(R.id.ceroCero);
        Button punto = findViewById(R.id.punto);
        Button sumaBtn = findViewById(R.id.suma);
        Button restaBtn = findViewById(R.id.menos);
        Button multiplicaBtn = findViewById(R.id.multiplica);
        Button divideBtn = findViewById(R.id.divide);
        Button potenciaBtn = findViewById(R.id.potencia);
        Button igualBtn = findViewById(R.id.igual);

        // BOTONES DE LOS NÚMEROS
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("1");
            }
        });

        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("2");
            }
        });

        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("3");
            }
        });

        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("4");
            }
        });

        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("5");
            }
        });

        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("6");
            }
        });

        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("7");
            }
        });

        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("8");
            }
        });

        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("9");
            }
        });

        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("0");
            }
        });

        ceroCero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append("00");
            }
        });

        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visor.append(".");
            }
        });

        // OPERACIONES
        // SUMA
        sumaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor1 = Double.parseDouble(visor.getText().toString());
                    suma = true;
                    visor.setText("");  // Limpiar el visor
                }
            }
        });

        // RESTA
        restaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor1 = Double.parseDouble(visor.getText().toString());
                    resta = true;
                    visor.setText("");
                }
            }
        });

        // MULTIPLICA
        multiplicaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor1 = Double.parseDouble(visor.getText().toString());
                    multiplicacion = true;
                    visor.setText("");
                }
            }
        });

        // DIVIDE
        divideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor1 = Double.parseDouble(visor.getText().toString());
                    division = true;
                    visor.setText("");
                }
            }
        });

        // POTENCIA
        potenciaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor1 = Double.parseDouble(visor.getText().toString());
                    potencia = true;
                    visor.setText("");
                }
            }
        });

        // BOTÓN IGUAL
        igualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visor.getText().length() != 0) {
                    valor2 = Double.parseDouble(visor.getText().toString());

                    if (suma) {
                        visor.setText(String.valueOf(valor1 + valor2));
                        suma = false;
                    }

                    if (resta) {
                        visor.setText(String.valueOf(valor1 - valor2));
                        resta = false;
                    }

                    if (multiplicacion) {
                        visor.setText(String.valueOf(valor1 * valor2));
                        multiplicacion = false;
                    }

                    if (division) {
                        if (valor2 != 0) {
                            visor.setText(String.valueOf(valor1 / valor2));
                        } else {
                            visor.setText("Error");  // No dividir entre 0
                        }
                        division = false;
                    }

                    if (potencia) {
                        visor.setText(String.valueOf(Math.pow(valor1, valor2)));
                        potencia = false;
                    }
                }
            }
        });
    }
}
