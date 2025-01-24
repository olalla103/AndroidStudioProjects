package com.example.proyectoconfragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {
    public static ArrayList<ListaEntrada> ENT_LISTA = new ArrayList<ListaEntrada>();
    public static Map<String, ListaEntrada> ENT_LISTA_HASHMAP = new HashMap<String, ListaEntrada>();

    public static class ListaEntrada {
        public String id;
        public int idImagen;
        public String textoEncima;
        public String textoDebajo;

        public ListaEntrada(String id, int idImagen, String textoEncima, String textoDebajo) {
            this.id = id;
            this.idImagen = idImagen;
            this.textoEncima = textoEncima;
            this.textoDebajo = textoDebajo;
        }

    }



    static {
        ponerEntrada(new ListaEntrada("1", R.drawable.bolsomarron, "Bolso Marrón", "Bolso marrón cuero"));
        ponerEntrada(new ListaEntrada("2", R.drawable.faldaplisadapicos_mujer, "Falda plisada picos", "Falda plisada picos"));
        ponerEntrada(new ListaEntrada("3", R.drawable.chaquetaacolchada_hombre, "Chaqueta acolchada", "Chaqueta acolchada"));
        ponerEntrada(new ListaEntrada("4", R.drawable.vaqueroballoon_mujer, "Vaquero ballon", "Vaquero balloon"));

    }

    private static void ponerEntrada(ListaEntrada entrada) {
        ENT_LISTA.add(entrada);
        ENT_LISTA_HASHMAP.put(entrada.id, entrada);
    }
}
