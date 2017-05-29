package Interfaces;/*
    PROPIEDADES
        BASICAS
            String ruta | consultable y modificable

        DERIVADAS
            String nombre | consultable

        COMPARTIDAS
            -
*/

import java.io.FileNotFoundException;

public interface Cancion
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    String getRuta();
	String getNombreFichero();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    void setRuta(String ruta) throws FileNotFoundException;
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    String[] extraerMetadatos();
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}