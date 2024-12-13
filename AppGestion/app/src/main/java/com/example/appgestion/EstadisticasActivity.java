package com.example.appgestion;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EstadisticasActivity extends AppCompatActivity {

    private ArrayList<PrendaRopa> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        // Recibir datos
        datos = (ArrayList<PrendaRopa>) getIntent().getSerializableExtra("datos");

        // Configurar estadísticas
        mostrarNumeroTotalPrendas();
        mostrarGraficoEstilos();
        mostrarGraficoTallas();
    }

    private void mostrarNumeroTotalPrendas() {
        TextView totalPrendas = findViewById(R.id.textViewTotalPrendas);
        totalPrendas.setText("Total de prendas: " + datos.size());
    }

    private void mostrarGraficoEstilos() {
        PieChart pieChart = findViewById(R.id.pieChartEstilos);

        // Contar prendas por estilo
        HashMap<Estilos, Integer> conteoEstilos = new HashMap<>();
        for (PrendaRopa prenda : datos) {
            Estilos estilo = prenda.getEstilo();
            conteoEstilos.put(estilo, conteoEstilos.getOrDefault(estilo, 0) + 1);
        }

        // Crear entradas para el gráfico
        List<PieEntry> pieEntries = new ArrayList<>();
        for (Estilos estilo : conteoEstilos.keySet()) {
            pieEntries.add(new PieEntry(conteoEstilos.get(estilo), estilo.name()));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Distribución por Estilo");
        PieData pieData = new PieData(dataSet);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    private void mostrarGraficoTallas() {
        BarChart barChart = findViewById(R.id.barChartTallas);

        // Contar prendas por talla
        HashMap<String, Integer> conteoTallas = new HashMap<>();
        for (PrendaRopa prenda : datos) {
            String talla = prenda.getTalla();
            conteoTallas.put(talla, conteoTallas.getOrDefault(talla, 0) + 1);
        }

        // Crear entradas para el gráfico
        List<BarEntry> barEntries = new ArrayList<>();
        int index = 0;
        for (String talla : conteoTallas.keySet()) {
            barEntries.add(new BarEntry(index, conteoTallas.get(talla)));
            index++;
        }

        BarDataSet dataSet = new BarDataSet(barEntries, "Distribución por Talla");
        BarData barData = new BarData(dataSet);

        barChart.setData(barData);
        barChart.invalidate();
    }
}
