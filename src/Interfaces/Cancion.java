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
    public String getRuta();
	public String getNombreFichero();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    public void setRuta(String ruta) throws FileNotFoundException;
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    public String[] extraerMetadatos();
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}