package Clases;
/*
    PROPIEDADES
        BASICAS
            ArrayList<Cancion> listaCanciones | consultable y modificable

        DERIVADAS
            int numeroCanciones | consultable

        COMPARTIDAS
            -
*/

import java.util.ArrayList;

public interface ListaDeReproduccion
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    ArrayList<CancionImpl> getListaCanciones();
    int getNumeroCanciones();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    void setListaCanciones(ArrayList<CancionImpl> listaCanciones);
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}