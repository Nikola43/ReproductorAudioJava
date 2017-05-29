package Interfaces;/*
    PROPIEDADES
        BASICAS
            String nombre                     | consultable y modificable
            ArrayList<Interfaces.Cancion> listaCanciones | consultable y modificable

        DERIVADAS
            int numeroCanciones | consultable

        COMPARTIDAS
            -
*/

import Clases.CancionImpl;

import java.util.ArrayList;

public interface ListaDeReproduccion
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    String getNombre();
    ArrayList<CancionImpl> getListaCanciones();
    int getNumeroCanciones();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    void setNombre(String nombre);
    void setListaCanciones(ArrayList<CancionImpl> listaCanciones);
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}