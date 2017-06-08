package Interfaces;
/*
    PROPIEDADES
        BASICAS
            String ruta | consultable y modificable

        DERIVADAS
            String nombreFichero | consultable

        COMPARTIDAS
            -
        RESTRICCIONES
            La ruta de la cancion debe ser la ruta de un fichero de audio valido en el sistema
*/

import Excepciones.CancionException;

public interface Cancion
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    String getRuta();
	String getNombreFichero();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    void setRuta(String ruta) throws CancionException;
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    String[] extraerMetadatos();
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}